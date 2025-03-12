package nl.duckacademy.stream.usernotification;

import io.grpc.stub.StreamObserver;
import nl.duckacademy.gRPC_server.grpc.Notification;
import nl.duckacademy.gRPC_server.grpc.NotificationServiceGrpc;
import nl.duckacademy.gRPC_server.grpc.UserNotificationId;

public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {
    @Override
    public void getNotification(UserNotificationId request, StreamObserver<Notification> responseObserver) {
        for (int index = 0; index < 10; index++) {
            Notification notification = Notification.newBuilder()
                    .setMessage("Not" + index).build();
            responseObserver.onNext(notification);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
