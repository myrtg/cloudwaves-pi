package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.entities.Commentaire;

public interface CommentaireRepository  extends JpaRepository<Commentaire,Integer> {
}