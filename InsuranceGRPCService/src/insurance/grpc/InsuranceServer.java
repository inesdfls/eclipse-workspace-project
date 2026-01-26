package insurance.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class InsuranceServer {

    public static void main(String[] args) throws Exception {

        Server server = ServerBuilder.forPort(9090)
                .addService(new InsuranceServiceImpl())
                .build();

        server.start();
        System.out.println("✅ gRPC Insurance running on port 9090");
        server.awaitTermination();
    }

    static class InsuranceServiceImpl extends InsuranceServiceGrpc.InsuranceServiceImplBase {

        @Override
        public void checkCoverage(CoverageRequest request,
                                  StreamObserver<CoverageResponse> responseObserver) {

            boolean validPolicy = request.getInsuranceId().startsWith("INS");
            boolean covered = validPolicy &&
                    ("MRI_SCAN".equals(request.getMedicalAct())
                     || "BLOOD_TEST".equals(request.getMedicalAct()));

            CoverageResponse res = CoverageResponse.newBuilder()
                    .setValidPolicy(validPolicy)
                    .setCovered(covered)
                    .setMessage(covered ? "Covered ✅" : "Not covered ❌")
                    .build();

            responseObserver.onNext(res);
            responseObserver.onCompleted();
        }
    }
}
