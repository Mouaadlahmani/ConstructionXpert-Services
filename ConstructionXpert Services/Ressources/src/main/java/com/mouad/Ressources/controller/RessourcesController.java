package com.mouad.Ressources.controller;

import com.mouad.Ressources.model.Ressources;
import com.mouad.Ressources.service.RessourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ressources/")
public class RessourcesController {

    @Autowired
    RessourcesService ressourcesService;

    @PostMapping("add/{id}")
    public Ressources addRessource(@PathVariable Long id, @RequestBody Ressources ressources){
        return ressourcesService.ajouterRessource(id, ressources);
    }

    @GetMapping("all")
    public List<Ressources> getAll(){
        return ressourcesService.getAllRessources();
    }

    @GetMapping("{id}")
    List<Ressources>getRessourcesByTache(@PathVariable Long id){
        return ressourcesService.getRessourcesByTache(id);
    }

    @GetMapping("ressource/{id}")
    Optional<Ressources> getRessourcesById(@PathVariable Long id){
        return ressourcesService.ressourceById(id);
    }

    @PutMapping("edit/{id}")
    public Ressources editRessource(@PathVariable Long id, @RequestBody Ressources ressources){
        return ressourcesService.editRessources(id, ressources);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        ressourcesService.deleteRessources(id);
    }

    @DeleteMapping("tache/{id}")
    public void deleteRessourcesOfTache(@PathVariable Long id){
        ressourcesService.deleteRessourcesWithTache(id);
    }
}
