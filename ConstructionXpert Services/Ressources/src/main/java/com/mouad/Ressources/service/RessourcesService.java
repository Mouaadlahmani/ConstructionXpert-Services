package com.mouad.Ressources.service;

import com.mouad.Ressources.model.Ressources;

import java.util.List;
import java.util.Optional;

public interface RessourcesService {
    Ressources ajouterRessource (Long id, Ressources ressources);
    Ressources editRessources (Long id, Ressources ressources);
    Optional<Ressources> ressourceById (Long id);
    List<Ressources> getAllRessources ();
    void deleteRessources(Long id);
    List<Ressources> getRessourcesByTache(Long id);
    void deleteRessourcesWithTache(Long id);
}
