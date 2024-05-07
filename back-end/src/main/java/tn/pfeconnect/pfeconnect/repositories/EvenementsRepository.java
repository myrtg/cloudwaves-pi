package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.Evenement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface EvenementsRepository extends JpaRepository<Evenement,Long> {
        @Query("select event from Evenement event order by event.nbPlace")
        List<Evenement> retrieveEventsOrderByNbplace();
    }

