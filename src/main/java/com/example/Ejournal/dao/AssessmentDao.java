package com.example.Ejournal.dao;

import com.example.Ejournal.entity.Assessment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentDao extends CrudRepository<Assessment, Long>
{
    Assessment findBySubject(String subject);
}
