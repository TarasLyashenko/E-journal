package com.example.Ejournal.service;

import com.example.Ejournal.entity.Student;

public interface StudentService
{
    void saveStudent(Student student);

    String createStudentsReport();

    Student getByid(long studentId);

}
