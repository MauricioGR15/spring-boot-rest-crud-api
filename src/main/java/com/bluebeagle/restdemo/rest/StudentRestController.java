package com.bluebeagle.restdemo.rest;

import com.bluebeagle.restdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    List<Student> studentsList;

    @PostConstruct
    public void loadData() {
        studentsList = new ArrayList<>();
        studentsList.add(new Student("Mauricio", "García"));
        studentsList.add(new Student("Damari", "Molina"));
        studentsList.add(new Student("Kenya", "García"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentsList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if(studentId >= studentsList.size() || studentId < 0) {
            throw  new StudentNotFoundException("Student ID not found for student - " + studentId);
        }

        return studentsList.get(studentId);
    }

}
