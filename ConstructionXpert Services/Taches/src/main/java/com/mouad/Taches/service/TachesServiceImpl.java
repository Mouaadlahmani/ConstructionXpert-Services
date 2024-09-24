package com.mouad.Taches.service;

import com.mouad.Taches.model.FullTachesResponse;
import com.mouad.Taches.model.Ressources;
import com.mouad.Taches.model.Taches;
import com.mouad.Taches.model.client.RessourcesClient;
import com.mouad.Taches.model.enums.Statut;
import com.mouad.Taches.repository.TachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TachesServiceImpl implements TachesService{

    @Autowired
    TachesRepository tachesRepository;
    @Autowired
    RessourcesClient ressourcesClient;

    @Override
    public Taches ajouterTache(Long id, Taches taches) {
        taches.setStatut(Statut.A_FAIRE);
        taches.setProjetId(id);
        return tachesRepository.save(taches);
    }

    @Override
    public Taches editTache(Long id, Taches taches) {
        Taches edited = new Taches();
        edited.setId(id);
        edited.setStatut(taches.getStatut());
        edited.setDescription(taches.getDescription());
        edited.setDateDebut(taches.getDateDebut());
        edited.setDateFin(taches.getDateFin());
        edited.setProjetId(taches.getProjetId());
        return tachesRepository.save(edited);
    }

    @Override
    public List<Taches> getAll() {
        return tachesRepository.findAll();
    }

    @Override
    public void deleteTaches(Long id) {
        ressourcesClient.deleteRessourcesByTache(id);
        tachesRepository.deleteById(id);
    }

    @Override
    public Optional<Taches> getTachesById(Long id) {
        return tachesRepository.findById(id);
    }

    @Override
    public Taches changerStatut(Long id, Taches taches) {
        Optional<Taches> optionalTaches = tachesRepository.findById(id);
        Taches statut = optionalTaches.get();
        statut.setStatut(taches.getStatut());
        return tachesRepository.save(statut);
    }

    @Override
    public List<Taches> getAllTachesByProjet(Long id) {
        return tachesRepository.findAllByProjetId(id);
    }

    @Override
    public void deleteAllTacheOfProjet(Long id) {
       List<Taches> taches = tachesRepository.findAllByProjetId(id);
         tachesRepository.deleteAll(taches);
    }

    @Override
    public FullTachesResponse tachWithRessources(Long id) {
        Taches tache = tachesRepository.findById(id).orElse(
                Taches.builder()
                        .description("NOT_FOUND")
                        .build()
        );
        List<Ressources> ressources = ressourcesClient.getRessourcesByTache(id);
        return FullTachesResponse.builder()
                .description(tache.getDescription())
                .dateDebut(tache.getDateDebut())
                .dateFin(tache.getDateFin())
                .statut(tache.getStatut())
                .ressources(ressources)
                .build();
    }

    @Override
    public List<Taches> findTachesWithSorting(String field) {
        return tachesRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Taches> findTachesWithPagination(int offset, int pageSize) {
        Page<Taches> taches = tachesRepository.findAll(PageRequest.of(offset, pageSize));
        return taches;
    }

    @Override
    public Page<Taches> findTachesWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<Taches> taches = tachesRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return taches;
    }
}
