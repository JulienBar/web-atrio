package com.webatrio.exercice.exercice.exception;

public class PersonneTropAgeeException extends Exception {

    public PersonneTropAgeeException() {
        super("Une personne doit être agée de moins de 150 ans.");
    }

}
