package com.example.Ejournal.service.impl;

import com.example.Ejournal.dao.AssessmentDao;
import com.example.Ejournal.entity.Assesment;
import com.example.Ejournal.service.AssesmentService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AssessmentServiceImpl implements AssesmentService
{
    @Resource
    private AssessmentDao assessmentDao;

    @Override
    public void saveAssessment(Assesment assessment)
    {
        assessmentDao.save(assessment);
    }

    @Override
    public Assesment getById(Long assessmentId)
    {
        Optional<Assesment> byId = assessmentDao.findById(assessmentId);
        return byId.get();
    }

    @Override
    public String createAssesmentReport(long studentId)
    {
        return null;
    }
}
