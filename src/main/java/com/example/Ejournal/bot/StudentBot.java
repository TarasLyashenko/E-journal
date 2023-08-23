package com.example.Ejournal.bot;

import com.example.Ejournal.dao.StudentDao;
import com.example.Ejournal.entity.Assesment;
import com.example.Ejournal.entity.Student;
import com.example.Ejournal.service.AssesmentService;
import com.example.Ejournal.service.StudentService;
import jakarta.annotation.Resource;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

public class StudentBot extends TelegramLongPollingBot
{
    @Resource
    private StudentService studentService;
    @Resource
    private AssesmentService assesmentService;
    @Resource
    private StudentDao studentDao;

    public StudentBot(String token)
    {
        super(token);
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        Message message = update.getMessage();
        long chatId = update.getMessage().getChatId();

        if (message.getText().startsWith("Ученик++"))
        {
            String[] lines = message.getText().split("\n");
            for (int i = 1; i < lines.length; i++)
            {
                String line = lines[i];
                String[] parts = line.split(" ");
                if (parts.length >= 4)
                {
                    String name = parts[0];
                    String surname = parts[1];
                    String gradeNumber = parts[2];
                    String phoneNumber = parts[3];

                    Student student = new Student();

                    student.setName(name);
                    student.setSurname(surname);
                    student.setGradeNumber(gradeNumber);
                    student.setPhoneNumber(phoneNumber);
                    studentService.saveStudent(student);

                    sendMessage(chatId, "Ученик " + name + " сохранен");
                }
            }
        }
        else if (message.getText().startsWith("Ученик+"))
        {
            String[] parts = message.getText().split(" ");
            if (parts.length == 5)
            {
                String name = parts[1];
                String surname = parts[2];
                String gradeNumber = parts[3];
                String phoneNumber = parts[4];

                Student student = new Student();

                student.setName(name);
                student.setSurname(surname);
                student.setGradeNumber(gradeNumber);
                student.setPhoneNumber(phoneNumber);
                studentService.saveStudent(student);

                sendMessage(chatId, "Ученик сохранен");
            }
        }

        else if ((message.getText().startsWith("Ученики")))
        {
            sendMessage(chatId, studentService.createStudentsReport());
        }
        else if ((message.getText().startsWith("Оценка+")))
        {
            String[] parts = message.getText().split(" ");
            if (parts.length == 4)
            {
                long studentId = Long.parseLong(parts[1]);
                String subject = parts[2];
                int score = Integer.parseInt(parts[3]);

                Student student = studentService.getByid(studentId);

                Assesment assesment = new Assesment();
                assesment.setStudent(student);
                assesment.setSubject(subject);
                assesment.setScore(score);
                assesment.setDate(LocalDate.now());
                assesmentService.saveAssessment(assesment);

                sendMessage(chatId, "Успешно сохранено");
            }
        }
        else if ((message.getText().startsWith("Оценки")))
        {
            String[] parts = message.getText().split(" ");
            String studentId = parts[1];
            long studentLongId = Long.parseLong(studentId);

            String assesmentReport = assesmentService.createAssesmentReport(studentLongId);
            sendMessage(chatId, assesmentReport);
            sendMessage(chatId, "Выполнено");

        }

        else
        {
            sendMessage(chatId, "Команда не распознана");
        }
        sendMessage(chatId, "Обработка завершена");
    }

    private void sendMessage(long chatId, String text)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try
        {
            execute(sendMessage);
        }
        catch (Exception e)
        {
            System.out.println("Ошибка при отправке сообщения: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername()
    {
        return "e_journalStudent_bot";
    }
}
