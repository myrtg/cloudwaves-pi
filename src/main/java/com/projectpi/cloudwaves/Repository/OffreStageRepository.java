package com.projectpi.cloudwaves.Repository;

import com.projectpi.cloudwaves.entites.Domaine;
import com.projectpi.cloudwaves.entites.OffreStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreStageRepository extends JpaRepository<OffreStage,Long> {
    OffreStage findByDomaine(Domaine domaine);
}
