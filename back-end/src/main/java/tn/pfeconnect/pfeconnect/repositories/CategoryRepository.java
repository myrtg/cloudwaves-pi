package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.entities.Categorie;

public interface CategoryRepository extends JpaRepository<Categorie,Long> {
}