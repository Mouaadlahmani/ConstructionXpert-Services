package com.mouad.Projets.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullProjetResponse {
    private String nom;
    private String description;
    private String dateDebut;
    private String dateFin;
    private Double budget;
    private List<Taches> taches;
}
