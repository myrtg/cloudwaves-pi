package com.projectpi.cloudwaves.Repository;

import com.projectpi.cloudwaves.entites.Domaine;
import com.projectpi.cloudwaves.entites.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions,Long> {
    List<Questions> findByCategory(String category);
    @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(@Param("category") String category,@Param("numQ") int numQ);
}
