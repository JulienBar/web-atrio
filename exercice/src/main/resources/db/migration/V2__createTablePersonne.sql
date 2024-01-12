CREATE TABLE personne (
    id SERIAL PRIMARY KEY,
    nom TEXT NOT NULL,
    prenom TEXT NOT NULL,
    date_naissance DATE NOT NULL
);

CREATE INDEX index_id_personne ON personne(id);