package com.example.Ejournal.dao;

import com.example.Ejournal.entity.Assesment;
import com.example.Ejournal.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentDao extends CrudRepository<Assesment, Long>
{
    @Query("SELECT a FROM Assesment a WHERE a.student = :student")
    List<Assesment> findGradesByStudent(@Param("student") Student student);
}
