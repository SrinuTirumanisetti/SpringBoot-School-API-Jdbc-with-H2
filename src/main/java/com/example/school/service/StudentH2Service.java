/*
 * You can use the following import statements
 * import org.springframework.http.HttpStatus;
 * import org.springframework.web.server.ResponseStatusException;
 *
 */

// Write your code here

package com.example.school.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import com.example.school.model.StudentRowMapper;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public List<Student> getStudents() {
        return db.query("SELECT * FROM STUDENT", new StudentRowMapper());
    }

    @Override
    public Student addStudent(Student student){
        db.update("INSERT INTO STUDENT(studentName,gender,standard) VALUES(?,?,?)",
        student.getStudentName(),student.getGender(),student.getStandard());

        Student savedStudent = db.queryForObject("SELECT * FROM STUDENT ORDER BY studentId DESC LIMIT 1",new StudentRowMapper());
        return savedStudent;
    }
}
