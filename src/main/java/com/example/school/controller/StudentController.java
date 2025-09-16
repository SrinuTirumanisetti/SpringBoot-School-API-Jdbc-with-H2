package com.example.school.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

@RestController
public class StudentController {

    private final StudentH2Service service;

    @Autowired
    public StudentController(StudentH2Service service) {
        this.service = service;
    }

    // API 1
    @GetMapping("/students")
    public List<Student> getStudents() {
        return service.getStudents();
    }

    // API 2
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return service.addStudent(student);
    }

    // API 4
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return service.getStudentById(studentId);
    }

    @PostMapping("/students/bulk")
    public String addStudentsBulk(@RequestBody ArrayList<Student> students) {
        return service.addStudentsBulk(students);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return service.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        service.deleteStudent(studentId);
    }
}
