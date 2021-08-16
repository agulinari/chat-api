package com.asapp.backend.challenge.exceptions;

public class InvalidFieldException extends Exception {

    private String field;

    public InvalidFieldException(String field) {
        super("Invalid field " + field);
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
