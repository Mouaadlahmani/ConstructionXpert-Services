package com.mouad.Taches.service;

import com.mouad.Taches.model.Taches;
import com.mouad.Taches.model.client.RessourcesClient;
import com.mouad.Taches.model.enums.Statut;
import com.mouad.Taches.repository.TachesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TachesServiceImplTest {

    @Mock
    private TachesRepository tachesRepository;

    @Mock
    private RessourcesClient ressourcesClient; // Ajout du mock pour RessourcesClient

    @InjectMocks
    private TachesServiceImpl tachesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAjouterTache() {
        Taches tache = new Taches();
        tache.setDescription("Nouvelle Tâche");

        when(tachesRepository.save(tache)).thenReturn(tache);

        Taches savedTache = tachesService.ajouterTache(1L, tache);

        assertNotNull(savedTache);
        assertEquals(Statut.A_FAIRE, savedTache.getStatut());
        assertEquals("Nouvelle Tâche", savedTache.getDescription());
    }

    @Test
    public void testEditTache() {
        Taches tache = new Taches();
        tache.setDescription("Tâche Modifiée");

        when(tachesRepository.save(any(Taches.class))).thenReturn(tache);

        Taches editedTache = tachesService.editTache(1L, tache);

        assertEquals("Tâche Modifiée", editedTache.getDescription());
    }

    @Test
    public void testGetAll() {
        List<Taches> tachesList = Arrays.asList(
                new Taches(1L, "Tâche 1", null, null, Statut.A_FAIRE, 1L),
                new Taches(2L, "Tâche 2", null, null, Statut.EN_COURS, 1L)
        );

        when(tachesRepository.findAll()).thenReturn(tachesList);

        List<Taches> result = tachesService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteTaches() {
        Long id = 1L;
        doNothing().when(ressourcesClient).deleteRessourcesByTache(id); // Mock du comportement du client
        doNothing().when(tachesRepository).deleteById(id);

        tachesService.deleteTaches(id);

        verify(ressourcesClient, times(1)).deleteRessourcesByTache(id); // Vérifie que la méthode a été appelée
        verify(tachesRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetTachesById() {
        Taches tache = new Taches(1L, "Tâche Test", null, null, Statut.A_FAIRE, 1L);
        when(tachesRepository.findById(1L)).thenReturn(Optional.of(tache));

        Optional<Taches> result = tachesService.getTachesById(1L);

        assertTrue(result.isPresent());
        assertEquals("Tâche Test", result.get().getDescription());
    }

    @Test
    public void testChangerStatut() {
        Taches tache = new Taches(1L, "Tâche Statut", null, null, Statut.A_FAIRE, 1L);
        when(tachesRepository.findById(1L)).thenReturn(Optional.of(tache));
        when(tachesRepository.save(tache)).thenReturn(tache);

        tache.setStatut(Statut.TERMINE);
        Taches updatedTache = tachesService.changerStatut(1L, tache);

        assertEquals(Statut.TERMINE, updatedTache.getStatut());
    }

    @Test
    public void testGetAllTachesByProjet() {
        List<Taches> tachesList = Arrays.asList(
                new Taches(1L, "Tâche Projet 1", null, null, Statut.A_FAIRE, 1L),
                new Taches(2L, "Tâche Projet 2", null, null, Statut.EN_COURS, 1L)
        );

        when(tachesRepository.findAllByProjetId(1L)).thenReturn(tachesList);

        List<Taches> result = tachesService.getAllTachesByProjet(1L);

        assertEquals(2, result.size());
        assertEquals("Tâche Projet 1", result.get(0).getDescription());
    }

    @Test
    public void testDeleteAllTacheOfProjet() {
        List<Taches> tachesList = Arrays.asList(
                new Taches(1L, "Tâche Projet 1", null, null, Statut.A_FAIRE, 1L),
                new Taches(2L, "Tâche Projet 2", null, null, Statut.EN_COURS, 1L)
        );

        when(tachesRepository.findAllByProjetId(1L)).thenReturn(tachesList);
        doNothing().when(tachesRepository).deleteAll(tachesList);

        tachesService.deleteAllTacheOfProjet(1L);

        verify(tachesRepository, times(1)).deleteAll(tachesList);
    }
}
