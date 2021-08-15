package com.asapp.backend.challenge.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoMessageEntity implements Serializable {

    private Integer id;
    private Integer messageId;
    private String url;
    private String source;

}
