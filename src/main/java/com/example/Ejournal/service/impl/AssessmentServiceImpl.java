package com.example.Ejournal.service.impl;

import com.example.Ejournal.dao.AssessmentDao;
import com.example.Ejournal.dao.StudentDao;
import com.example.Ejournal.entity.Assesment;
import com.example.Ejournal.entity.Student;
import com.example.Ejournal.service.AssesmentService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AssessmentServiceImpl implements AssesmentService
{
    @Resource
    private AssessmentDao assessmentDao;
    @Resource
    private StudentDao studentDao;

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
        StringBuilder responseBuilder = new StringBuilder();

        Optional<Student> byId = studentDao.findById(studentId);
        Student student = byId.get();
        List<Assesment> gradesByStudent = assessmentDao.findGradesByStudent(student);

        for (Assesment assesment : gradesByStudent)
        {
            LocalDate date = assesment.getDate();
            String subject = assesment.getSubject();
            int score = assesment.getScore();

            responseBuilder.
                    append(date).append(" ").
                    append(subject).append(" ").
                    append(score).append("\n");
        }
        return responseBuilder.toString();
    }

    @Override
    public Double calculateQuarterGrade(long studentId, String subject)
    {
        List<Integer> grades = assessmentDao.findScoreByStudentAndSubject(studentId, subject);

        if (grades.isEmpty())
        {
            return 0.0;
        }

        int sum = 0;
        for (Integer grade : grades)
        {
            sum += grade;
        }

        double average = (double) sum / grades.size();
        return Math.ceil(average);
    }
}