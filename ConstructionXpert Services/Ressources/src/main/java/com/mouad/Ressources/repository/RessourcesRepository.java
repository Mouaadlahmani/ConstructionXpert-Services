package com.mouad.Ressources.repository;

import com.mouad.Ressources.model.Ressources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourcesRepository extends JpaRepository<Ressources, Long> {
}
