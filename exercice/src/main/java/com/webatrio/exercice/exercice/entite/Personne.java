package com.webatrio.exercice.exercice.entite;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    @NotBlank(message = "Le nom d'une personne est obligatoire.")
    private String nom;

    @Column(name = "prenom", nullable = false)
    @NotBlank(message = "Le prénom d'une personne est obligatoire.")
    private String prenom;

    @Column(name = "date_naissance", nullable = false)
    @NotNull
    private LocalDate dateNaissance;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Emploi> emplois;
}
