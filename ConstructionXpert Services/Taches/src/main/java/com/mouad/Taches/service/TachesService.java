package com.mouad.Taches.service;

import com.mouad.Taches.model.FullTachesResponse;
import com.mouad.Taches.model.Taches;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TachesService {
    Taches ajouterTache(Long id, Taches taches);
    Taches editTache(Long id, Taches taches);
    List<Taches> getAll();
    void deleteTaches(Long id);
    Optional<Taches> getTachesById(Long id);
    Taches changerStatut(Long id, Taches taches);
    List<Taches> getAllTachesByProjet(Long id);
    void deleteAllTacheOfProjet(Long id);
    FullTachesResponse tachWithRessources(Long id);
    List<Taches>findTachesWithSorting(String field);
    Page<Taches> findTachesWithPagination(int offset, int pageSize);
    Page<Taches> findTachesWithPaginationAndSorting(int offset, int pageSize, String field);

}
