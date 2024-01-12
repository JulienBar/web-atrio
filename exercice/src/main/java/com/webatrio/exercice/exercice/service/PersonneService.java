package com.webatrio.exercice.exercice.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.webatrio.exercice.exercice.entite.Emploi;
import com.webatrio.exercice.exercice.entite.Personne;
import com.webatrio.exercice.exercice.exception.PersonneTropAgeeException;
import com.webatrio.exercice.exercice.repository.EmploiRepository;
import com.webatrio.exercice.exercice.repository.PersonneRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class PersonneService {
    
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private EmploiRepository emploiRepository;

    public PersonneService(PersonneRepository personneRepository, EmploiRepository emploiRepository) {
        this.personneRepository = personneRepository;
        this.emploiRepository = emploiRepository;
    }

    public Personne createPersonne(Personne personne) throws PersonneTropAgeeException {
        checkAge(personne);
        return personneRepository.save(personne);
    }

    public Personne ajouterEmploi(Emploi emploi, String idPersonne) throws EntityNotFoundException {
        Long id = Long.parseLong(idPersonne);
        Personne personne = personneRepository.findById(id).orElse(null);
        if (personne == null) {
            throw new EntityNotFoundException("Cette personne n'existe pas.");
        }

        personne.getEmplois().add(emploiRepository.save(emploi));
        return personneRepository.save(personne);
    }

    private void checkAge(Personne personne) throws PersonneTropAgeeException {
        LocalDate now = LocalDate.now();
        int age = Period.between(personne.getDateNaissance(), now).getYears();
        if (age >= 150) {
            throw new PersonneTropAgeeException();
        }
    }

    public List<Emploi> getAllEmploisParDates(String idPersonne, LocalDate dateDebut, LocalDate dateFin) {
        if (idPersonne == null || dateDebut == null) {
            return new ArrayList<>();
        }
        Long id = Long.valueOf(idPersonne);
        Personne personne = new Personne();
        personne.setId(id);
        if (dateFin != null) {
            return emploiRepository.findAllByPersonneAndDateDebutGreaterThanEqualAndDateFinLessThanEqual(personne, dateDebut, dateFin);
        } else {
            return emploiRepository.findAllByPersonneAndDateDebutGreaterThanEqual(personne, dateDebut);
        }
    }

        //public List<Personne> getAllPersonnesParEntreprise(String nomEntreprise) {
        //    if (!StringUtils.hasText(nomEntreprise)) {
        //        return new ArrayList<>();
        //     }

        //     return personneRepository.findAllByNomEntreprise(nomEntreprise);
        //}
}
