package orchestrator.logic;

import orchestrator.clients.GraphQLClient;
import orchestrator.clients.NotificationClient;
import orchestrator.model.CareRequest;
import orchestrator.model.RequestStatus;
import orchestrator.storage.RequestStore;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import insurance.grpc.CoverageRequest;
import insurance.grpc.CoverageResponse;
import insurance.grpc.InsuranceServiceGrpc;

public class OrchestratorEngine {

    private final NotificationClient notify = new NotificationClient();
    private final GraphQLClient graph = new GraphQLClient();

    // SOAP
    public boolean callIdentitySOAP(String nationalId) {
        orchestrator.soap.client.IdentityWebServiceService service =
                new orchestrator.soap.client.IdentityWebServiceService();
        orchestrator.soap.client.IdentityWebService port =
                service.getIdentityWebServicePort();
        return port.verifyIdentity(nationalId);
    }

    // gRPC
    public boolean callInsuranceGRPC(String insuranceId, String act, double cost) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        InsuranceServiceGrpc.InsuranceServiceBlockingStub stub =
                InsuranceServiceGrpc.newBlockingStub(channel);

        CoverageResponse res = stub.checkCoverage(
                CoverageRequest.newBuilder()
                        .setInsuranceId(insuranceId)
                        .setMedicalAct(act)
                        .setEstimatedCost(cost)
                        .build()
        );

        channel.shutdown();
        return res.getCovered();
    }

    public void process(CareRequest req) {
        try {
            req.addHistory("SUBMITTED", "Request received by orchestrator");
            notify.send(req.getRequestId(), "Request submitted.");

            // 1) SOAP Identity
            if (!callIdentitySOAP(req.getNationalId())) {
                req.setStatus(RequestStatus.IDENTITY_FAILED);
                req.addHistory("IDENTITY", "Identity failed");
                notify.send(req.getRequestId(), "Rejected: Identity failed.");
                RequestStore.update(req);
                return;
            }
            req.setStatus(RequestStatus.IDENTITY_VERIFIED);
            req.addHistory("IDENTITY", "Identity verified");

            // 2) gRPC Insurance
            if (!callInsuranceGRPC(req.getInsuranceId(), req.getMedicalAct(), req.getEstimatedCost())) {
                req.setStatus(RequestStatus.INSURANCE_REJECTED);
                req.addHistory("INSURANCE", "Insurance rejected");
                notify.send(req.getRequestId(), "Rejected: Insurance rejected.");
                RequestStore.update(req);
                return;
            }
            req.setStatus(RequestStatus.INSURANCE_VALIDATED);
            req.addHistory("INSURANCE", "Insurance validated");

            // 3) GraphQL risk
            String riskJson = graph.score(req.getMedicalAct(), req.getJustification(), req.getEstimatedCost());
            String riskLevel = riskJson.contains("HIGH") ? "HIGH" : (riskJson.contains("MEDIUM") ? "MEDIUM" : "LOW");
            double conf = riskJson.contains("0.9") ? 0.9 : (riskJson.contains("0.7") ? 0.7 : 0.8);

            req.setRiskLevel(riskLevel);
            req.setConfidence(conf);
            req.setStatus(RequestStatus.RISK_SCORED);
            req.addHistory("RISK", "Risk=" + riskLevel + " confidence=" + conf);

            // 4) Rules
            if ("HIGH".equals(riskLevel) && req.getEstimatedCost() > 1000) {
                req.setStatus(RequestStatus.RULES_REJECTED);
                req.addHistory("RULES", "Rejected: high risk + high cost");
                notify.send(req.getRequestId(), "Rejected: rules (high risk + high cost).");
                RequestStore.update(req);
                return;
            }

            // 5) Docs
            if (req.getJustification() == null || !req.getJustification().toLowerCase().contains("report")) {
                req.setStatus(RequestStatus.DOCS_MISSING);
                req.addHistory("DOCS", "Missing medical report");
                notify.send(req.getRequestId(), "Suspended: missing documents.");
                RequestStore.update(req);
                return;
            }
            req.setStatus(RequestStatus.DOCS_VALIDATED);
            req.addHistory("DOCS", "Documents validated");

            // 6) Expert review
            if (req.getMedicalAct() != null && req.getMedicalAct().toLowerCase().contains("surgery")) {
                req.setStatus(RequestStatus.EXPERT_REJECTED);
                req.addHistory("EXPERT", "Expert rejected");
                notify.send(req.getRequestId(), "Rejected by expert.");
                RequestStore.update(req);
                return;
            }
            req.setStatus(RequestStatus.EXPERT_APPROVED);
            req.addHistory("EXPERT", "Expert approved");

            // 7) Cost
            req.setReimbursement(req.getEstimatedCost() * 0.7);
            req.setCopay(req.getEstimatedCost() * 0.3);
            req.setStatus(RequestStatus.COST_CALCULATED);
            req.addHistory("COST", "Reimbursement=70% Copay=30%");

            // 8) Finance
            if (req.getCopay() > 500) {
                req.setStatus(RequestStatus.FIN_REJECTED);
                req.addHistory("FINANCE", "Financial authorization failed");
                notify.send(req.getRequestId(), "Rejected: financial authorization failed.");
                RequestStore.update(req);
                return;
            }
            req.setStatus(RequestStatus.FIN_AUTHORIZED);
            req.addHistory("FINANCE", "Financial authorization OK");

            // FINAL
            req.setStatus(RequestStatus.COMPLETED_APPROVED);
            req.addHistory("FINAL", "Approved");
            notify.send(req.getRequestId(), "Approved ✅");

            RequestStore.update(req);

        } catch (Exception e) {
            req.setStatus(RequestStatus.ERROR_EXTERNAL_SERVICE);
            req.addHistory("ERROR", e.getMessage());
            RequestStore.update(req);
        }
    }
}