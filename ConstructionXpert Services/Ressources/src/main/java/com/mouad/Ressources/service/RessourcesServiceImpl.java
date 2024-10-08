package com.mouad.Ressources.service;


import com.mouad.Ressources.model.Ressources;
import com.mouad.Ressources.repository.RessourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RessourcesServiceImpl implements RessourcesService{

    @Autowired
    RessourcesRepository ressourcesRepository;


    @Override
    public Ressources ajouterRessource(Long id, Ressources ressources) {
        ressources.setTacheId(id);
        return ressourcesRepository.save(ressources);
    }

    @Override
    public Ressources editRessources(Long id, Ressources ressources) {
        Optional<Ressources> edited = ressourcesRepository.findById(id);
        Ressources saved = edited.get();
        saved.setId(id);
        saved.setNom(ressources.getNom());
        saved.setQuantity(ressources.getQuantity());
        saved.setTacheId(ressources.getTacheId());
        return ressourcesRepository.save(saved);
    }

    @Override
    public Optional<Ressources> ressourceById(Long id) {
        return ressourcesRepository.findById(id);
    }

    @Override
    public List<Ressources> getAllRessources() {
        return ressourcesRepository.findAll();
    }

    @Override
    public void deleteRessources(Long id) {
        ressourcesRepository.deleteById(id);
    }

    @Override
    public List<Ressources> getRessourcesByTache(Long id) {
        return ressourcesRepository.getRessourcesByTacheId(id);
    }

    @Override
    public void deleteRessourcesWithTache(Long id) {
        List<Ressources> ressources = ressourcesRepository.getRessourcesByTacheId(id);
        ressourcesRepository.deleteAll(ressources);
    }
}
