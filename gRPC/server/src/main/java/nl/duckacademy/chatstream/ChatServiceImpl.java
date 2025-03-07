package nl.duckacademy.chatstream;

import io.grpc.stub.StreamObserver;
import nl.duckacademy.gRPC_server.grpc.ChatMessage;
import nl.duckacademy.gRPC_server.grpc.ChatServiceGrpc;

public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {
    @Override
    public StreamObserver<ChatMessage> chat(StreamObserver<ChatMessage> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(ChatMessage chatMessage) {
                System.out.println("Received: " + chatMessage.getUser() + ": " + chatMessage.getMessage());

                // Send a response back
                responseObserver.onNext(ChatMessage.newBuilder()
                        .setUser("Server")
                        .setMessage("Echo: " + chatMessage.getMessage())
                        .build());
            }
            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
            @Override
            public void onCompleted() {
                System.out.println("Chat ended.");
                responseObserver.onCompleted();
            }
        };
    }
}
