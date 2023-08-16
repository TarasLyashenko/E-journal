package com.example.Ejournal.config;

import com.example.Ejournal.bot.StudentBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans
{
    @Bean
    public StudentBot createTelegramBot()
    {
        return new StudentBot("6523894730:AAGNI1HzUGrr2nSd_WJ22o0Rr1SWaY0b3Pg");
    }
}
