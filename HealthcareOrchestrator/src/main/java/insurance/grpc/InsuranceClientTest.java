package insurance.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class InsuranceClientTest {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        InsuranceServiceGrpc.InsuranceServiceBlockingStub stub =
                InsuranceServiceGrpc.newBlockingStub(channel);

        CoverageResponse res = stub.checkCoverage(
                CoverageRequest.newBuilder()
                        .setInsuranceId("INS-001")
                        .setMedicalAct("MRI_SCAN")
                        .setEstimatedCost(450)
                        .build()
        );

        System.out.println("Message = " + res.getMessage());
        System.out.println("validPolicy = " + res.getValidPolicy());
        System.out.println("covered = " + res.getCovered());

        channel.shutdown();
    }
}
