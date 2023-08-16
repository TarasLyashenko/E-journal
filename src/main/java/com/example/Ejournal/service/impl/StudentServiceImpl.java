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
}
