package com.example.Ejournal.service;

import com.example.Ejournal.entity.Assesment;

public interface AssesmentService
{
    void saveAssessment(Assesment assessment);

    Assesment getById(Long assessmentId);

    String createAssesmentReport(long studentId);
}
