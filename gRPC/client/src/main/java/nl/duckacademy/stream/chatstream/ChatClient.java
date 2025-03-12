package nl.duckacademy.stream.chatstream;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import nl.duckacademy.gRPC_server.grpc.ChatMessage;
import nl.duckacademy.gRPC_server.grpc.ChatServiceGrpc;

import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceStub asyncStub = ChatServiceGrpc.newStub(channel);

        StreamObserver<ChatMessage> requestObserver = asyncStub.chat(new StreamObserver<>() {
            @Override
            public void onNext(ChatMessage response) {
                System.out.println(response.getUser() + ": " + response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Server closed the connection.");
            }
        });
        // Read messages from the console and send them
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("You: ");
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("exit")) {
                requestObserver.onCompleted();
                break;
            }
            requestObserver.onNext(ChatMessage.newBuilder().setUser("Client").setMessage(message).build());
        }
        channel.shutdown();
    }
}
