package com.asapp.backend.challenge.resources;

import com.asapp.backend.challenge.exceptions.InvalidFieldException;
import com.asapp.backend.challenge.exceptions.RequiredFieldException;
import lombok.Data;

@Data
public class TextContent extends Content {

    private String type;
    private String text;

    @Override
    public void validate() throws RequiredFieldException, InvalidFieldException {
        if (!type.equals("text")) {
            throw new InvalidFieldException("type");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new RequiredFieldException("text");
        }
    }
}
