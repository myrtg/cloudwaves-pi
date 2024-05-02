package com.projectpi.cloudwaves.Repository;

import com.projectpi.cloudwaves.entites.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature,Long> {
    @Query("SELECT c FROM Candidature c WHERE c.Utilisateur.idUser = :userId")
    List<Candidature> findByUserId(Long userId);
}
