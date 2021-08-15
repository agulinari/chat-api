package com.asapp.backend.challenge.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity implements Serializable {

    private Integer id;
    private LocalDateTime timestamp;
    private Integer sender;
    private Integer recipient;
    private String type;
    private String text;

}
