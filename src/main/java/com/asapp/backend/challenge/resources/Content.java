package com.asapp.backend.challenge.resources;

import com.asapp.backend.challenge.exceptions.InvalidFieldException;
import com.asapp.backend.challenge.exceptions.RequiredFieldException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextContent.class, name = "text"),
        @JsonSubTypes.Type(value = ImageContent.class, name = "image"),
        @JsonSubTypes.Type(value = VideoContent.class, name = "video")})
public abstract class Content {

    public abstract String getType();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getText() {
        return null;
    }

    public abstract void validate() throws RequiredFieldException, InvalidFieldException;

}
