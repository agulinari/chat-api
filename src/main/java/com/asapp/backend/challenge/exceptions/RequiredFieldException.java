package com.asapp.backend.challenge.exceptions;

public class RequiredFieldException extends Exception {

    private String field;

    public RequiredFieldException(String field) {
        super("Field " + field + " is required");
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
