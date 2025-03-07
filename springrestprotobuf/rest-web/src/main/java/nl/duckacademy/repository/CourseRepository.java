package nl.duckacademy.repository;

import nl.duckacademy.protobuf.Training;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    private final List<Training.Course> courses;

    public CourseRepository (List<Training.Course> courses) {
        this.courses = courses;
    }

    public Training.Course getCourse(int id) {
        return courses.stream().filter(c->c.getId() == id).findFirst()
                .orElseThrow(() ->new IllegalArgumentException("not found"));
    }
    public Training.Course createCourse(Training.CourseInput courseInput) {
        var newCourse = this.courseFromCourseInput(courseInput);
        courses.add(newCourse);
        return newCourse;
    }
    private Training.Course courseFromCourseInput(Training.CourseInput courseInput) {
        return Training.Course.newBuilder()
                .setId(courseInput.getId())
                .setCourseName(courseInput.getCourseName())
                .build();
    }
}
