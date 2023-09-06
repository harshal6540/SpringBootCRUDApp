package com.example.springcrudjpa.dao;

import com.example.springcrudjpa.entity.Student;

import java.util.List;

public interface StudentDAO {
     void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    void update (Student theStudent);
    void delete (Integer id);
}

