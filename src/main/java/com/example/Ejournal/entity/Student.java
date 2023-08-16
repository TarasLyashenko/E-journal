package com.example.Ejournal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private String gradeNumber;
    private String phoneNumber;

    public Student(String name, String surname, String gradeNumber, String phoneNumber)
    {
        this.name = name;
        this.surname = surname;
        this.gradeNumber = gradeNumber;
        this.phoneNumber = phoneNumber;
    }

    public Student()
    {

    }
}
