package helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import nl.duckacademy.gRPC_server.grpc.HelloRequest;
import nl.duckacademy.gRPC_server.grpc.HelloResponse;
import nl.duckacademy.gRPC_server.grpc.HelloServiceGrpc;
import nl.duckacademy.helloworld.HelloServiceImpl;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloWorldTest {
    private static Server server;
    private static ManagedChannel channel;

    @BeforeAll
    static void setUp() throws Exception {
        // Create an in-process server
        //server grpc
        server = ServerBuilder
                //grcp port server
                .forPort(8080)
                //grcp services
                .addService(new HelloServiceImpl())
                .build();

        //server start
        server.start();
        System.out.println("Server started");
        //server.awaitTermination();
        //CHANNEL
        channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext().build();
    }

    @AfterAll
    static void tearDown() throws Exception {
        channel.shutdownNow();
        server.shutdownNow();
    }

    @Test
    void should_helloworld() {
        //given
        final HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        final String firstName = "Rick";
        //WHEN
        HelloResponse helloResponse = stub
                .hello(HelloRequest.newBuilder().setFirstName(firstName).setLastName("J").build());
        //channel.shutdown();
        //THEN
        System.out.println(helloResponse.getGreeting());
        assertThat(helloResponse).isNotNull();
        assertThat(helloResponse.getGreeting()).contains("Hello, "+firstName);
    }
    @Test
    void should_helloworldV2() {
        //given
        final HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        final String firstName = "Rick";
        //WHEN
        HelloResponse helloResponse = stub
                .hello(HelloRequest.newBuilder().setFirstName(firstName).setLastName("J").build());
        //channel.shutdown();
        //THEN
        System.out.println(helloResponse.getGreeting());
        assertThat(helloResponse).isNotNull();
        assertThat(helloResponse.getGreeting()).contains("Hello, "+firstName);
    }
}
