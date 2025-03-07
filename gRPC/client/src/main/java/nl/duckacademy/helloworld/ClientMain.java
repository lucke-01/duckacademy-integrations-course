package nl.duckacademy.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import nl.duckacademy.gRPC_server.grpc.HelloRequest;
import nl.duckacademy.gRPC_server.grpc.HelloResponse;
import nl.duckacademy.gRPC_server.grpc.HelloServiceGrpc;

public class ClientMain {
    public static void main(String[] args) {
        //client grpc
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext().build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub
                .hello(HelloRequest.newBuilder().setFirstName("Rick").setLastName("J").build());
        channel.shutdown();
        System.out.println(helloResponse.getGreeting());
    }
}