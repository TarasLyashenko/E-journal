package com.example.Ejournal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Assessment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Student student;

    private String subject;
    private int score;
    private LocalDate date;

    public Assessment()
    {

    }

    public Assessment(long studentId, String subject, int score)
    {
        this.id = studentId;
        this.subject = subject;
        this.score = score;
    }
}
