package nl.duckacademy.userstream;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import nl.duckacademy.gRPC_server.grpc.StreamResult;
import nl.duckacademy.gRPC_server.grpc.UserData;
import nl.duckacademy.gRPC_server.grpc.UserStreamGrpc;

public class UserStreamClientMain {
    public static void main(String[] args) {
        //client grpc
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext().build();

        UserStreamGrpc.UserStreamStub asyncStub = UserStreamGrpc.newStub(channel);
        StreamObserver<StreamResult> responseObserver = new StreamObserver<StreamResult>() {
            @Override
            public void onNext(StreamResult summary) {
                System.out.println("END" + summary.getResult());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Finished clientSideStreamingGetStatisticsOfStocks");
            }
        };

        StreamObserver<UserData> requestObserver = asyncStub.userStream(responseObserver);

        for (int index = 0; index < 10; index++) {
            UserData userData = UserData.newBuilder().setData("userdata: " + index).build();
            System.out.println("sending: " + userData);
            requestObserver.onNext(userData);
        }
        requestObserver.onCompleted();
        // Wait a bit before closing to ensure all messages are sent
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.shutdown();
    }
}
