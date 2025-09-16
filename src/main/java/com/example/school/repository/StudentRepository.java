package com.example.school.repository;

import java.util.*;
import com.example.school.model.Student;

public interface StudentRepository{
    List<Student> getStudents();
    Student addStudent(Student student);
    Student getStudentById(int studentId);
}