package nl.duckacademy.controller;

import nl.duckacademy.protobuf.Training;
import nl.duckacademy.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepo;

    @GetMapping("/courses/{id}")
    Training.Course customer(@PathVariable(value = "id") Integer id) {
        return courseRepo.getCourse(id);
    }
    @PostMapping("/courses")
    Training.Course createCourse(@RequestBody Training.CourseInput courseInput) {
        System.out.println("Inserting course");
        System.out.println(courseInput.getCourseName());
        return courseRepo.createCourse(courseInput);
    }
}
