package nl.duckacademy.usermanager;

import io.grpc.stub.StreamObserver;
import nl.duckacademy.gRPC_server.grpc.OperationStatusResponse;
import nl.duckacademy.gRPC_server.grpc.Status;
import nl.duckacademy.gRPC_server.grpc.UserManagerServiceGrpc;
import nl.duckacademy.gRPC_server.grpc.UserRequest;

import java.util.Map;

public class UserManagerServiceImpl extends UserManagerServiceGrpc.UserManagerServiceImplBase {
    @Override
    public void createUser(UserRequest request, StreamObserver<OperationStatusResponse> responseObserver) {
        OperationStatusResponse result;
        if (!request.hasLastname()) {
            result = OperationStatusResponse.newBuilder()
                    .setStatus(Status.KO)
                    .putAllResults(Map.of("lastname", "lastname was not found","general", "check fields"))
                    .build();
        } else {
            result = OperationStatusResponse.newBuilder()
                    .setStatus(Status.OK)
                    .putResults("general", "Everything is fine")
                    .build();
        }
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
