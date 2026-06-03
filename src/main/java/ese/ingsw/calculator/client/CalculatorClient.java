package ese.ingsw.calculator.client;

import ese.ingsw.calculator.CalculatorGrpc;
import ese.ingsw.calculator.OperationRequest;
import ese.ingsw.calculator.OperationResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50077)
                .usePlaintext()
                .build();

        CalculatorGrpc.CalculatorBlockingStub stub = CalculatorGrpc.newBlockingStub(channel);

        OperationRequest request = OperationRequest.newBuilder()
                .setOpA(1)
                .setOpB(2)
                .build();

        OperationResponse response = stub.add(request);

        System.out.println("Risultato: " + response);

        channel.shutdown();
    }
}
