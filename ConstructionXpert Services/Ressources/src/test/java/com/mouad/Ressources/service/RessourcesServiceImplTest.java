package com.mouad.Ressources.service;

import com.mouad.Ressources.model.Ressources;
import com.mouad.Ressources.repository.RessourcesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RessourcesServiceImplTest {

    @InjectMocks
    private RessourcesServiceImpl ressourcesService;

    @Mock
    private RessourcesRepository ressourcesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAjouterRessource() {
        Ressources ressource = new Ressources(null, "Ressource 1", 10.0, 1L);
        when(ressourcesRepository.save(ressource)).thenReturn(ressource);

        Ressources savedRessource = ressourcesService.ajouterRessource(1L, ressource);

        assertNotNull(savedRessource);
        assertEquals("Ressource 1", savedRessource.getNom());
        assertEquals(10.0, savedRessource.getQuantity());
        assertEquals(1L, savedRessource.getTacheId());
    }

    @Test
    public void testEditRessources() {
        Ressources existingRessource = new Ressources(1L, "Ressource 1", 10.0, 1L);
        Ressources updatedRessource = new Ressources(null, "Updated Resource", 20.0, 1L);

        when(ressourcesRepository.findById(1L)).thenReturn(Optional.of(existingRessource));
        when(ressourcesRepository.save(any(Ressources.class))).thenReturn(updatedRessource);

        Ressources result = ressourcesService.editRessources(1L, updatedRessource);

        assertNotNull(result);
        assertEquals("Updated Resource", result.getNom());
        assertEquals(20.0, result.getQuantity());
    }

    @Test
    public void testRessourceById() {
        Ressources ressource = new Ressources(1L, "Ressource 1", 10.0, 1L);
        when(ressourcesRepository.findById(1L)).thenReturn(Optional.of(ressource));

        Optional<Ressources> result = ressourcesService.ressourceById(1L);

        assertTrue(result.isPresent());
        assertEquals("Ressource 1", result.get().getNom());
    }

    @Test
    public void testGetAllRessources() {
        List<Ressources> ressourcesList = new ArrayList<>();
        ressourcesList.add(new Ressources(1L, "Ressource 1", 10.0, 1L));
        ressourcesList.add(new Ressources(2L, "Ressource 2", 5.0, 1L));

        when(ressourcesRepository.findAll()).thenReturn(ressourcesList);

        List<Ressources> result = ressourcesService.getAllRessources();

        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteRessources() {
        doNothing().when(ressourcesRepository).deleteById(1L);

        ressourcesService.deleteRessources(1L);

        verify(ressourcesRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetRessourcesByTache() {
        List<Ressources> ressourcesList = new ArrayList<>();
        ressourcesList.add(new Ressources(1L, "Ressource 1", 10.0, 1L));

        when(ressourcesRepository.getRessourcesByTacheId(1L)).thenReturn(ressourcesList);

        List<Ressources> result = ressourcesService.getRessourcesByTache(1L);

        assertEquals(1, result.size());
        assertEquals("Ressource 1", result.get(0).getNom());
    }

    @Test
    public void testDeleteRessourcesWithTache() {
        List<Ressources> ressourcesList = new ArrayList<>();
        ressourcesList.add(new Ressources(1L, "Ressource 1", 10.0, 1L));
        when(ressourcesRepository.getRessourcesByTacheId(1L)).thenReturn(ressourcesList);

        doNothing().when(ressourcesRepository).deleteAll(ressourcesList);

        ressourcesService.deleteRessourcesWithTache(1L);

        verify(ressourcesRepository, times(1)).deleteAll(ressourcesList);
    }
}
