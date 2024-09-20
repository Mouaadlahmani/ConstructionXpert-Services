package com.mouad.Projets.service;

import com.mouad.Projets.client.TachesClient;
import com.mouad.Projets.model.FullProjetResponse;
import com.mouad.Projets.model.Projets;
import com.mouad.Projets.model.Taches;
import com.mouad.Projets.repository.ProjetsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjetsServiceImplTest {

    @InjectMocks
    private ProjetsServiceImpl projetsService;

    @Mock
    private ProjetsRepository projetsRepository;

    @Mock
    private TachesClient tachesClient;

    private Projets projet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        projet = new Projets(1L, "Projet Test", "Description", Date.valueOf("2024-01-01"), Date.valueOf("2024-12-31"), 1000.0);
    }

    @Test
    public void testAjouterProjet() {
        when(projetsRepository.save(projet)).thenReturn(projet);
        Projets result = projetsService.ajouterProjet(projet);
        assertEquals(projet, result);
        verify(projetsRepository, times(1)).save(projet);
    }

    @Test
    public void testModifierProjet() {
        when(projetsRepository.findById(1L)).thenReturn(Optional.of(projet));
        projet.setNom("Projet Modifié");
        when(projetsRepository.save(projet)).thenReturn(projet);

        Projets result = projetsService.modifierProjet(1L, projet);
        assertEquals("Projet Modifié", result.getNom());
        verify(projetsRepository, times(1)).save(projet);
    }

    @Test
    public void testAllProjets() {
        when(projetsRepository.findAll()).thenReturn(Arrays.asList(projet));
        List<Projets> result = projetsService.allProjets();
        assertEquals(1, result.size());
        assertEquals(projet, result.get(0));
    }

    @Test
    public void testSupprimerProjet() {
        // Arrange
        Projets projet = new Projets();
        projet.setId(1L);
        projet.setNom("Test Projet");
        projetsRepository.save(projet);

        // Act
        projetsService.supprimerProjet(projet.getId());

        // Assert
        Optional<Projets> deletedProjet = projetsRepository.findById(projet.getId());
        assertFalse(deletedProjet.isPresent(), "Le projet devrait avoir été supprimé");
    }


    @Test
    public void testProjetWithTaches() {
        when(projetsRepository.findById(1L)).thenReturn(Optional.of(projet));
        when(tachesClient.findAllTachesByProjet(1L)).thenReturn(Arrays.asList(new Taches()));

        FullProjetResponse result = projetsService.projetWithTaches(1L);
        assertEquals(projet.getNom(), result.getNom());
        assertEquals(1, result.getTaches().size());
    }
}
