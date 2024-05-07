package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.Notification;
import tn.pfeconnect.pfeconnect.entities.Projet;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projet, Long> {
    List<Notification> findNotificationByIdProjet (Long idProjet);
}