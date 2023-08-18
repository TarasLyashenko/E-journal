package com.example.Ejournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Assessment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Student student;

    private String subject;
    private int score;
    private LocalDate date;

    public Assessment()
    {

    }

    public Assessment(long studentId, String subject, int score)
    {
        this.id = student.getId();
        this.subject = subject;
        this.score = score;
    }
}
