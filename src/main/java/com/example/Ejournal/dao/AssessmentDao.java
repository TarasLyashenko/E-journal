package com.example.Ejournal.dao;

import com.example.Ejournal.entity.Assesment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentDao extends CrudRepository<Assesment, Long>
{
    Assesment findBySubject(String subject);
}
