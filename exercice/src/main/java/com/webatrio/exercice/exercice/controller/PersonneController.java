package com.webatrio.exercice.exercice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.webatrio.exercice.exercice.entite.Emploi;
import com.webatrio.exercice.exercice.entite.Personne;
import com.webatrio.exercice.exercice.exception.PersonneTropAgeeException;
import com.webatrio.exercice.exercice.service.PersonneService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }
    
    @PostMapping("/personne")
    public ResponseEntity<?> createPersonne(@Valid @RequestBody Personne personne) {
        try {
            return ResponseEntity
                .ok()
                .body(personneService.createPersonne(personne));
        } catch (PersonneTropAgeeException e) {
            return ResponseEntity
                .badRequest()
                .body(e.getLocalizedMessage());
        }
    }

    @PostMapping("/personne/{idpersonne}/emploi")
    public ResponseEntity<?> ajouterEmploi(@Valid @RequestBody Emploi emploi, @PathVariable(value="idpersonne") String idPersonne) {
        try {
            return ResponseEntity
            .ok()
            .body(personneService.ajouterEmploi(emploi, idPersonne));
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            return ResponseEntity
                .badRequest()
                .body("L'id de la personne est incorrect.");
        }
    }

    @GetMapping("/personne/{idPersonne}/emplois")
    public ResponseEntity<?> getAllEmplois(@RequestParam LocalDate dateDebut, @RequestParam Optional<LocalDate> dateFin, @PathVariable(value="idPersonne") String idPersonne) {
        try {
            LocalDate fin = dateFin.orElse(null);
            return ResponseEntity
            .ok()
            .body(personneService.getAllEmploisParDates(idPersonne, dateDebut, fin));
        } catch (NumberFormatException e) {
            return ResponseEntity
                .badRequest()
                .body("L'id de la personne est incorrect.");
        }
    }

    @GetMapping("/personnes/entreprise")
    public ResponseEntity<?> getAllPersonnesParEntreprses(@RequestParam String nomEntreprise) {
        return ResponseEntity
        .ok()
        .body("");//personneService.getAllPersonnesParEntreprise(nomEntreprise));
    }
}
