package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.entities.Forum;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum,Integer> {
    List<Forum> findByTitreContainingIgnoreCase(String titre);
}
