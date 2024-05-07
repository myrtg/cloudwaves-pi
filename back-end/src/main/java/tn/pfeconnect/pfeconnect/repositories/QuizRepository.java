package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
