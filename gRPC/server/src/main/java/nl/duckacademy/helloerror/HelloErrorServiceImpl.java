package nl.duckacademy.helloerror;

import io.grpc.Metadata;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import nl.duckacademy.gRPC_server.grpc.ErrorResponse;
import nl.duckacademy.gRPC_server.grpc.HelloErrorServiceGrpc;
import nl.duckacademy.gRPC_server.grpc.HelloRequest;
import nl.duckacademy.gRPC_server.grpc.HelloResponse;

public class HelloErrorServiceImpl extends HelloErrorServiceGrpc.HelloErrorServiceImplBase {
    @Override
    public void helloWithException(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        if (request.getFirstName().length() < 2) {
            this.manageError(responseObserver);
            return;
        }
        System.out.println("Request received from client:\n" + request);
        String greeting = new StringBuilder()
                .append("Hello, ").append(request.getFirstName()).append(" ")
                .append(request.getLastName()).toString();

        HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void manageError(StreamObserver<HelloResponse> responseObserver) {
        Metadata.Key<ErrorResponse> errorResponseKey = ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance());
        ErrorResponse errorResponse = ErrorResponse.newBuilder()
                .setInformation("firstName length should be greater than 2")
                .build();
        Metadata metadata = new Metadata();
        metadata.put(errorResponseKey, errorResponse);
        responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("FirstName bad parameter")
                .asRuntimeException(metadata));
    }
}
