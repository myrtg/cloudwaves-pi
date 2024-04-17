package com.cloudwaves.pfeconnect.repositories;

import com.projectpi.cloudwaves.entites.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvenementsRepository extends JpaRepository<Evenement,Long> {
    @Query("select event from Evenement event order by event.nbPlace")
    List<Evenement> retrieveEventsOrderByNbplace();
}

