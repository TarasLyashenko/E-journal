package com.example.Ejournal.bot;

import com.example.Ejournal.entity.Student;
import com.example.Ejournal.service.StudentService;
import jakarta.annotation.Resource;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StudentBot extends TelegramLongPollingBot
{
    @Resource
    private StudentService studentService;

    public StudentBot(String token)
    {
        super(token);
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        Message message = update.getMessage();
        long chatId = update.getMessage().getChatId();

        if (message.getText().startsWith("Ученик+"))
        {
            String[] parts = message.getText().split(" ");
            if (parts.length == 5)
            {
                String name = parts[1];
                String surname = parts[2];
                String gradeNumber = parts[3];
                String phoneNumber = parts[4];

                Student student = new Student(name,surname,gradeNumber,phoneNumber);
                studentService.saveStudent(student);
                sendMessage(chatId, "Ученик сохранен");
            }
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
