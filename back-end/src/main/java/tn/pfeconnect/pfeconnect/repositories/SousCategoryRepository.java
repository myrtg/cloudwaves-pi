package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.entities.SousCategorie;

public interface SousCategoryRepository extends JpaRepository<SousCategorie, Long> {
}