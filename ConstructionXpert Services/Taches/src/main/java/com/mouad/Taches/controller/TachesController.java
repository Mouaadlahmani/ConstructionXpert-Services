package com.mouad.Taches.controller;

import com.mouad.Taches.dto.APIResponse;
import com.mouad.Taches.model.FullTachesResponse;
import com.mouad.Taches.model.Taches;
import com.mouad.Taches.service.TachesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taches")
@RequiredArgsConstructor
public class TachesController {

    @Autowired
    TachesService tachesService;

    @PostMapping("/add/{id}")
    public Taches addTache(@PathVariable Long id, @RequestBody Taches taches){
        return tachesService.ajouterTache(id, taches);
    }

    @GetMapping("/all")
    public List<Taches> getAll(){
        return tachesService.getAll();
    }

    @GetMapping("/tache/{id}")
    public Optional<Taches> getTacheById(@PathVariable Long id){
        return tachesService.getTachesById(id);
    }

    @GetMapping("/{id}")
    public FullTachesResponse tachesWithRessources(@PathVariable Long id){
        return tachesService.tachWithRessources(id);
    }

    @GetMapping("/sort/{field}")
    public APIResponse<List<Taches>> getTachesWithSort(@PathVariable String field) {
        List<Taches> allTaches = tachesService.findTachesWithSorting(field);
        return new APIResponse<>(allTaches.size(), allTaches);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public APIResponse<Page<Taches>> getTachesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Taches> tachesWithPagination = tachesService.findTachesWithPagination(offset, pageSize);
        return new APIResponse<>(tachesWithPagination.getSize(), tachesWithPagination);
    }

    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public APIResponse<Page<Taches>> getTachesWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Taches> tachesWithPagination = tachesService.findTachesWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponse<>(tachesWithPagination.getSize(), tachesWithPagination);
    }

    @GetMapping("/projet/{id}")
    public List<Taches> tachesOfProjet(@PathVariable Long id){
        return tachesService.getAllTachesByProjet(id);
    }

    @PutMapping("/edit/{id}")
    public Taches editTache(@PathVariable Long id, @RequestBody Taches taches){
        return tachesService.editTache(id, taches);
    }

    @PutMapping("/statut/{id}")
    public Taches changeStatut(@PathVariable Long id, @RequestBody Taches taches){
        return tachesService.changerStatut(id, taches);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        tachesService.deleteTaches(id);
    }

    @DeleteMapping("/projet/delete/{id}")
    public void deleteTachesOfProjet(@PathVariable Long id){
        tachesService.deleteAllTacheOfProjet(id);
    }


}
