package com.mouad.Projets.model;

import com.mouad.Projets.model.enums.Statut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taches {
    private String description;
    private String dateDebut;
    private String dateFin;
    private Statut statut;
}
