package nl.duckacademy.helloerror;

import io.grpc.*;
import io.grpc.protobuf.ProtoUtils;
import nl.duckacademy.gRPC_server.grpc.ErrorResponse;
import nl.duckacademy.gRPC_server.grpc.HelloErrorServiceGrpc;
import nl.duckacademy.gRPC_server.grpc.HelloRequest;
import nl.duckacademy.gRPC_server.grpc.HelloResponse;

public class HelloErrorMain {
    public static void main(String[] args) {
        //client grpc
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext().build();

        HelloErrorServiceGrpc.HelloErrorServiceBlockingStub stub = HelloErrorServiceGrpc.newBlockingStub(channel);

        try {
            HelloResponse helloResponse = stub
                    .helloWithException(HelloRequest.newBuilder().setFirstName("1").setLastName("J").build());
            System.out.println(helloResponse.getGreeting());
        } catch (StatusRuntimeException statusException) {
            Metadata metadata = Status.trailersFromThrowable(statusException);
            System.out.println("STATUS");
            System.out.println(statusException.getStatus());
            System.out.println(statusException.getStatus().getCode());
            System.out.println(statusException.getStatus().getDescription());
            System.out.println("METADATA");
            System.out.println(metadata);
            ErrorResponse errorResponse = metadata.get(ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance()));
            System.out.println("Error response");
            System.out.println(errorResponse);
        }
        channel.shutdown();

    }
}
