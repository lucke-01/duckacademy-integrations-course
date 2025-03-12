package nl.duckacademy.usermanager;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import nl.duckacademy.gRPC_server.grpc.*;

import java.util.ArrayList;
import java.util.List;

public class UserManagerClient {
    public static void main(String[] args) {
        ManagedChannel channel =  ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext().build();

        UserManagerServiceGrpc.UserManagerServiceBlockingStub userManagerStub = UserManagerServiceGrpc.newBlockingStub(channel);

        //user request
        List<Course> courses = createCourses(3);
        UserRequest userRequest = UserRequest.newBuilder()
                .setName("name")
                .setLastname("lastName")
                .setCategory(Category.EMPLOYEE)
                .setSalary(5000.0)
                .addAllCourses(courses)
                .build();
        //end user request

        OperationStatusResponse createUserResponse = userManagerStub.createUser(userRequest);
        System.out.println(createUserResponse);
    }
    private static List<Course> createCourses(int number) {
        List<Course> courses = new ArrayList<>();
        for (int index = 0; index < number; index++) {
            var course = Course.newBuilder()
                    .setId(index)
                    .setName("course"+index)
                    .addAllLabels(List.of("learn", "grow"))
                    .build();
            courses.add(course);
        }
        return courses;
    }
}
