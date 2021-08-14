package com.asapp.backend.challenge.resources;

import lombok.Data;

@Data
public class ImageContent  extends Content {

    private String type;
    private String url;
    private Integer height;
    private Integer width;

}
