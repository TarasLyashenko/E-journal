package com.example.Ejournal.service.impl;

import com.example.Ejournal.dao.StudentDao;
import com.example.Ejournal.entity.Student;
import com.example.Ejournal.service.StudentService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService
{
    @Resource
    private StudentDao studentDao;

    @Override
    public void saveStudent(Student student)
    {
        studentDao.save(student);
    }

    @Override
    public String createStudentsReport()
    {
        StringBuilder responseBuilder = new StringBuilder();

        Iterable<Student> all = studentDao.findAll();
        for (Student student : all)
        {
            long id = student.getId();
            String gradeNumber = student.getGradeNumber();
            String surname = student.getSurname();
            String name = student.getName();
            String phoneNumber = student.getPhoneNumber();

            responseBuilder.
                    append(id).append(" - ").
                    append(gradeNumber).append(" ").
                    append(name).append(" ").
                    append(surname).append(" ").
                    append(phoneNumber).append("\n");
        }
        return responseBuilder.toString();
    }

    @Override
    public Student getByid(long studentId)
    {
        Optional<Student> byId = studentDao.findById(studentId);
        Student student = byId.get();
        return student;
    }

}
