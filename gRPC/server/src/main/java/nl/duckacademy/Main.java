package nl.duckacademy;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import nl.duckacademy.stream.chatstream.ChatServiceImpl;
import nl.duckacademy.helloworld.HelloServiceImpl;
import nl.duckacademy.usermanager.UserManagerServiceImpl;
import nl.duckacademy.stream.usernotification.NotificationServiceImpl;
import nl.duckacademy.stream.userstream.UserStreamServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        //server grpc
        Server server = ServerBuilder
                //grcp port server
                .forPort(8080)
                //grcp services
                .addService(new HelloServiceImpl())
                .addService(new ChatServiceImpl())
                .addService(new NotificationServiceImpl())
                .addService(new UserStreamServiceImpl())
                .addService(new UserManagerServiceImpl())
                .build();

        //server start
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }
}