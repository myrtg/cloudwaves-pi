package com.cloudwaves.pfeconnect.repositories;

import com.projectpi.cloudwaves.entites.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionsRepository extends JpaRepository<Inscription,Long> {
}
