package orchestrator.model;

import java.util.ArrayList;
import java.util.List;

public class CareRequest {

    private String requestId;
    private String patientName;
    private String nationalId;
    private String insuranceId;

    private String medicalAct;
    private double estimatedCost;
    private String justification;

    private String riskLevel;
    private double confidence;

    private double reimbursement;
    private double copay;

    private RequestStatus status;
    private List<HistoryEntry> history = new ArrayList<>();

    public CareRequest() {}

    public void addHistory(String step, String message) {
        history.add(new HistoryEntry(step, message));
    }

    // getters/setters
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }

    public String getInsuranceId() { return insuranceId; }
    public void setInsuranceId(String insuranceId) { this.insuranceId = insuranceId; }

    public String getMedicalAct() { return medicalAct; }
    public void setMedicalAct(String medicalAct) { this.medicalAct = medicalAct; }

    public double getEstimatedCost() { return estimatedCost; }
    public void setEstimatedCost(double estimatedCost) { this.estimatedCost = estimatedCost; }

    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }

    public double getReimbursement() { return reimbursement; }
    public void setReimbursement(double reimbursement) { this.reimbursement = reimbursement; }

    public double getCopay() { return copay; }
    public void setCopay(double copay) { this.copay = copay; }

    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }

    public List<HistoryEntry> getHistory() { return history; }
    public void setHistory(List<HistoryEntry> history) { this.history = history; }
}