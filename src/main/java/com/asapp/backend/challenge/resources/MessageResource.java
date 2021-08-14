package com.asapp.backend.challenge.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResource<T extends Content> {

    private Integer id;
    private LocalDateTime timestamp;
    private Integer sender;
    private Integer recipient;
    private T content;
}
