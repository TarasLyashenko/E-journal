package com.example.Ejournal.dao;

import com.example.Ejournal.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student, Long>
{
    Student findByName(String name);
}
