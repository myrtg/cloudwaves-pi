package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.Candidature;

import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature,Long> {
    @Query("SELECT c FROM Candidature c WHERE c.Utilisateur.id = :userId")
    List<Candidature> findByUserId(Long userId);
}
