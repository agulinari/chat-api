package com.asapp.backend.challenge.controller.requests;

import com.asapp.backend.challenge.resources.Content;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest<T extends Content> {

    private Integer sender;
    private Integer recipient;
    private T content;

}
