package com.asapp.backend.challenge.resources;

import com.asapp.backend.challenge.exceptions.InvalidFieldException;
import com.asapp.backend.challenge.exceptions.RequiredFieldException;
import com.asapp.backend.challenge.resources.enums.ContentTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextContent extends Content {

    private String type;
    private String text;

    @Override
    public void validate() throws RequiredFieldException, InvalidFieldException {
        if (!ContentTypeEnum.TEXT.getValue().equals(type)) {
            throw new InvalidFieldException("type");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new RequiredFieldException("text");
        }
    }
}
