package com.example.Ejournal.service.impl;

import com.example.Ejournal.dao.StudentDao;
import com.example.Ejournal.entity.Student;
import com.example.Ejournal.service.StudentService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public String seeAllStudent()
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
                    append(gradeNumber).append(" ").
                    append(surname).append(" ").
                    append(name).append(" ").
                    append(phoneNumber).append("\n");
        }
        return responseBuilder.toString();
    }
}
