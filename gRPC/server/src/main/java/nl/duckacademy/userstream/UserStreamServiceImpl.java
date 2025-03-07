package nl.duckacademy.userstream;

import io.grpc.stub.StreamObserver;
import nl.duckacademy.gRPC_server.grpc.StreamResult;
import nl.duckacademy.gRPC_server.grpc.UserData;
import nl.duckacademy.gRPC_server.grpc.UserStreamGrpc;

public class UserStreamServiceImpl extends UserStreamGrpc.UserStreamImplBase {
    @Override
    public StreamObserver<UserData> userStream(StreamObserver<StreamResult> responseObserver) {
        return new StreamObserver<UserData>() {
            int count;
            StringBuffer sb = new StringBuffer();

            @Override
            public void onNext(UserData userData) {
                count++;
                sb.append(":")
                        .append(userData.getData());
                System.out.println("receiving data from user" + userData.getData());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("SERVER completed");
                responseObserver.onNext(StreamResult.newBuilder()
                        .setResult(sb.toString())
                        .build());
                responseObserver.onCompleted();
            }
        };
    }
}
