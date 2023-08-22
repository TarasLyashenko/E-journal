package com.example.Ejournal;

import com.example.Ejournal.bot.StudentBot;
import com.example.Ejournal.dao.AssessmentDao;
import com.example.Ejournal.dao.StudentDao;
import com.example.Ejournal.entity.Student;
import com.example.Ejournal.service.AssesmentService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Optional;

@SpringBootApplication
public class EJournalApplication implements CommandLineRunner
{
    @Resource
    private StudentBot studentBot;
    @Resource
    private AssessmentDao assessmentDao;
    @Resource
    private StudentDao studentDao;

    public static void main(String[] args)
    {
        SpringApplication.run(EJournalApplication.class, args);
    }

    @PostConstruct
    public void registerBot()
    {
        try
        {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(studentBot);
        }
        catch (TelegramApiException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(String... args) throws Exception
    {
        Optional<Student> byId = studentDao.findById(1151L);
        Student student = byId.get();


        System.out.println(assessmentDao.findGradesByStudent(student));
    }
}
