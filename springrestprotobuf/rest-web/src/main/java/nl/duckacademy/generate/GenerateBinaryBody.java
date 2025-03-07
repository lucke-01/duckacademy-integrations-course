package nl.duckacademy.generate;

import nl.duckacademy.protobuf.Training;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateBinaryBody {
    public static void main(String[] args) throws IOException {
        // Assuming Training.CourseInput is your protobuf message
        Training.CourseInput courseInput = Training.CourseInput.newBuilder()
                .setId(1)
                .setCourseName("New Course")
                .build();

        // Write to file
        try (FileOutputStream output = new FileOutputStream("./protobuf_bin/course_input.bin")) {
            courseInput.writeTo(output);
        }
        System.out.println("Protobuf request file generated: course_input.bin");
    }
}
