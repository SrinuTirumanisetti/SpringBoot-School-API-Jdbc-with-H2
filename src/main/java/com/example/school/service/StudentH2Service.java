package com.example.school.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

        Student savedStudent = db.queryForObject(
            "SELECT * FROM STUDENT ORDER BY studentId DESC LIMIT 1",
            new StudentRowMapper()
        );
        return savedStudent;
    }

    @Override
    public Student getStudentById(int studentId){
        try {
            Student student = db.queryForObject(
                "SELECT * FROM STUDENT WHERE studentId=?",
                new StudentRowMapper(),
                studentId
            );
            return student;
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String addStudentsBulk(ArrayList<Student> students) {
        for(Student student : students) {
            db.update("INSERT INTO STUDENT(studentName,gender,standard) VALUES(?,?,?)",
                student.getStudentName(), student.getGender(), student.getStandard());
        }
        return "Successfully added " + students.size() + " students";
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        int rows = db.update(
            "UPDATE STUDENT SET studentName=?, gender=?, standard=? WHERE studentId=?",
            student.getStudentName(), student.getGender(), student.getStandard(), studentId
        );

        if(rows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return getStudentById(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        int rows = db.update("DELETE FROM STUDENT WHERE studentId=?", studentId);
        if(rows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
