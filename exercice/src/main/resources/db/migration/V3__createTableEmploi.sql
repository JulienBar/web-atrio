CREATE TABLE emploi (
    id SERIAL PRIMARY KEY,
    nom_entreprise VARCHAR(255) NOT NULL,
    poste TEXT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE,
    personne_id INTEGER,
    FOREIGN KEY (personne_id) REFERENCES personne(id)
);

CREATE INDEX index_id_emploi ON emploi(id);