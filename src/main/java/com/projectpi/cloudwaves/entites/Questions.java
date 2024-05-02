package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Questions")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Questions")
    private Integer id;
    private String category;
    private String questionTitle;
    private  String option1;
    private  String option2;
    private  String option3;
    private  String option4;
    private  String correctAnswer;
}
