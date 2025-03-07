package nl.duckacademy.config;

import nl.duckacademy.protobuf.Training;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    /*
    The ProtobufHttpMessageConverter bean is used to convert responses
     returned by @RequestMapping annotated methods to protocol buffer messages.
     */
    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
    @Bean
    public List<Training.Course> courses() {
        Training.Course course1 = Training.Course.newBuilder()
                .setId(1)
                .setCourseName("REST with Spring")
                .addAllStudent(new ArrayList<>())
                .build();
        Training.Course course2 = Training.Course.newBuilder()
                .setId(2)
                .setCourseName("Learn Spring Security")
                .addAllStudent(new ArrayList<>())
                .build();
        return new ArrayList<>(List.of(course1,course2));
    }
}
