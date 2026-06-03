package ese.ingsw.calculator.server;

import ese.ingsw.calculator.CalculatorGrpc;
import ese.ingsw.calculator.OperationRequest;
import ese.ingsw.calculator.OperationResponse;
import ese.ingsw.calculator.OperationResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorGrpc.CalculatorImplBase {

    @Override
    public void add(OperationRequest request, StreamObserver<OperationResponse> responseObserver) {
        int res = request.getOpA() + request.getOpB();
        OperationResponse or = OperationResponse.newBuilder().setRes(res).build();

        responseObserver.onNext(or);
        responseObserver.onCompleted(); // chiudere la connessione
    }

    @Override
    public void subtract(OperationRequest request, StreamObserver<OperationResponse> responseObserver) {
        int res = request.getOpA() - request.getOpB();
        OperationResponse or = OperationResponse.newBuilder().setRes(res).build();

        responseObserver.onNext(or);
        responseObserver.onCompleted(); // chiudere la connessione
    }

    @Override
    public void multiply(OperationRequest request, StreamObserver<OperationResponse> responseObserver) {
        int res = request.getOpA() * request.getOpB();
        OperationResponse or = OperationResponse.newBuilder().setRes(res).build();

        responseObserver.onNext(or);
        responseObserver.onCompleted(); // chiudere la connessione
    }

    @Override
    public void divide(OperationRequest request, StreamObserver<OperationResponse> responseObserver) {
        int a = request.getOpA();
        int b = request.getOpB();

        if (b == 0) {
            Status erroreStatus = Status.INVALID_ARGUMENT
                    .withDescription("Errore: Divisione per zero non permessa!");
            responseObserver.onError(erroreStatus.asRuntimeException());
            return;
        }

        int res = a / b;
        OperationResponse or = OperationResponse.newBuilder().setRes(res).build();

        responseObserver.onNext(or);
        responseObserver.onCompleted(); // chiudere la connessione
    }
}
