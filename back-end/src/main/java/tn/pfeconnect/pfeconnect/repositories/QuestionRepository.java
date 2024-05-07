package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.Questions;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
    List<Questions> findByCategory(String category);
    @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);
}
