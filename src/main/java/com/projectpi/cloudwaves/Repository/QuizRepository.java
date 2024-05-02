package com.projectpi.cloudwaves.Repository;

import com.projectpi.cloudwaves.entites.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.image.BufferedImage;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

}
