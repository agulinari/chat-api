package com.asapp.backend.challenge.resources;

import lombok.Data;

@Data
public class VideoContent extends Content {

    private String type;
    private String url;
    private String source;

}
