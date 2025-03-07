package nl.duckacademy.usernotification;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import nl.duckacademy.gRPC_server.grpc.NotificationServiceGrpc;
import nl.duckacademy.gRPC_server.grpc.UserNotificationId;

public class UserNotificationClientMain {
    public static void main(String[] args) {
        //client grpc
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext().build();

        NotificationServiceGrpc.NotificationServiceBlockingStub stub =
                NotificationServiceGrpc.newBlockingStub(channel);
        var userNotId = UserNotificationId.newBuilder().setUserId(1L).build();
        var notificationObserver = stub.getNotification(userNotId);
        notificationObserver.forEachRemaining(notification -> {
            System.out.printf("notification %s%n", notification.getMessage());
        });
        channel.shutdown();
        System.out.println("null");
    }
}