package com.asapp.backend.challenge.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageMessageEntity implements Serializable {

    private Integer id;
    private Integer messageId;
    private String url;
    private Integer height;
    private Integer width;

}
