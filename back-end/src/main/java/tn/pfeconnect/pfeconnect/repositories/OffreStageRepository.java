package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.OffreStage;
import tn.pfeconnect.pfeconnect.enums.Domaine;

@Repository
public interface OffreStageRepository extends JpaRepository<OffreStage,Long> {
    OffreStage findByDomaine(Domaine domaine);
}