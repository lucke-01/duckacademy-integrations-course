package nl.duckacademy;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import nl.duckacademy.services.HelloServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        //server grpc
        Server server = ServerBuilder
                //grcp port server
                .forPort(8080)
                //grcp services
                .addService(new HelloServiceImpl())
                .build();

        //server start
        server.start();
        server.awaitTermination();
    }
}