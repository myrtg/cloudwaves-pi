package com.cloudwaves.pfeconnect.services;

import com.cloudwaves.pfeconnect.entities.Inscription;

import java.util.List;

public interface InscriptionService {
    Inscription addInscription(Inscription inscription);
    Inscription updateInscription(Inscription inscription);

    void removeInscription(Long idInscription);

    Inscription retrieveInscription(Long idInscription);

    List<Inscription> retrieveInscriptions();
}
