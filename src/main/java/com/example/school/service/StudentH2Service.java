/*
 * You can use the following import statements
 * import org.springframework.http.HttpStatus;
 * import org.springframework.web.server.ResponseStatusException;
 *
 */

// Write your code here

package com.example.school.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;


import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import com.example.school.model.StudentRowMapper;

@Service
public class StudentH2Service implements StudentRepository{

    @Autowired
    public JdbcTemplate db;

    @Override
    public List<Student> getStudents(){
        return db.query("SELECT * FROM STUDENT",new StudentRowMapper());
    }
}