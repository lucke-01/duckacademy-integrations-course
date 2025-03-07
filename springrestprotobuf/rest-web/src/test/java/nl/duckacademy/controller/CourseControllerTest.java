package nl.duckacademy.controller;

import com.google.protobuf.InvalidProtocolBufferException;
import nl.duckacademy.protobuf.Training;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_getCourse() throws Exception {
        MvcResult result = mockMvc.perform(get("/courses/1")
                        .accept("application/x-protobuf"))  // Expect Protobuf response
                .andExpect(status().isOk())
                .andReturn();

        byte[] responseBytes = result.getResponse().getContentAsByteArray();

        // Assuming Training.Course is a Protobuf message class
        Training.Course course = Training.Course.parseFrom(responseBytes);
        assertThat(course).isNotNull();
        assertThat(course.getId()).isEqualTo(1);
        System.out.println(course);
    }
    @Test
    public void should_createCourse() throws Exception {
        // Create Protobuf request object
        Training.CourseInput courseInput = Training.CourseInput.newBuilder()
                .setId(1)
                .setCourseName("New Protobuf Course")
                .build();

        // Convert Protobuf to byte array
        byte[] requestBody = courseInput.toByteArray();
        // Perform POST request with Protobuf binary data
        MvcResult result = mockMvc.perform(post("/courses")
                        .contentType("application/x-protobuf")  // Set content type to protobuf
                        .accept("application/x-protobuf")  // Expect Protobuf response
                        .content(requestBody))  // Send Protobuf binary data
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andReturn();
        // Extract response as byte array
        byte[] responseBytes = result.getResponse().getContentAsByteArray();
        // Convert response back to Protobuf object
        Training.Course createdCourse = Training.Course.parseFrom(responseBytes);
        // Validate the response
        assertThat(createdCourse.getId()).isEqualTo(1);
        assertThat(createdCourse.getCourseName()).isEqualTo("New Protobuf Course");
    }
}
