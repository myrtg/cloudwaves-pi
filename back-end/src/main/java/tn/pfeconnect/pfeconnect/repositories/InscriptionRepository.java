package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.entities.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
}
