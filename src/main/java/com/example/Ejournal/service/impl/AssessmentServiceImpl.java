package com.example.Ejournal.service.impl;

import com.example.Ejournal.dao.AssessmentDao;
import com.example.Ejournal.entity.Assessment;
import com.example.Ejournal.service.AssesmentService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AssessmentServiceImpl implements AssesmentService
{
    @Resource
    private AssessmentDao assessmentDao;

    @Override
    public void saveAssessment(Assessment assessment)
    {
        assessmentDao.save(assessment);
    }
}
