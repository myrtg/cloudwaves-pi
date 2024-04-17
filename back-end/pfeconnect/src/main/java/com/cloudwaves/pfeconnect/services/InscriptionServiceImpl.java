package com.cloudwaves.pfeconnect.services;

import com.cloudwaves.pfeconnect.entities.Inscription;
import com.cloudwaves.pfeconnect.repositories.InscriptionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InscriptionServiceImpl implements InscriptionService{
    InscriptionsRepository inscriptionsRepository;
    @Override
    public Inscription addInscription(Inscription inscription) {
        return inscriptionsRepository.save(inscription);
    }

    @Override
    public Inscription updateInscription(Inscription inscription) {
        return inscriptionsRepository.save(inscription);
    }

    @Override
    public void removeInscription(Long idInscription) {
        inscriptionsRepository.deleteById(idInscription);
    }

    @Override
    public Inscription retrieveInscription(Long idInscription) {
        return inscriptionsRepository.findById(idInscription).get();
    }

    @Override
    public List<Inscription> retrieveInscriptions() {
        return inscriptionsRepository.findAll();
    }
}
