package com.webatrio.exercice.exercice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webatrio.exercice.exercice.entite.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Long>{

    List<Personne> findAllByOrderByNom();

  //  @Query(value = "select * from personne where id in (select personne_id from emploi where LOWER(nom_entreprise) = LOWER(#{nomEntreprise}))")
  //  List<Personne> findAllByNomEntreprise(String nomEntreprise);
}