package com.webatrio.exercice.exercice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webatrio.exercice.exercice.entite.Emploi;
import com.webatrio.exercice.exercice.entite.Personne;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EmploiRepository extends CrudRepository<Emploi, Long>{

    List<Emploi> findAllByPersonneAndDateDebutGreaterThanEqualAndDateFinLessThanEqual(Personne personne, LocalDate dateDebut, LocalDate dateFin);

    List<Emploi> findAllByPersonneAndDateDebutGreaterThanEqual(Personne personne, LocalDate dateDebut);
}
