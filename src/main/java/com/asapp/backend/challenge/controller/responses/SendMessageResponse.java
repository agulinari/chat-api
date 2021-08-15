package com.asapp.backend.challenge.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageResponse {

    private Integer id;
    private LocalDateTime timestamp;

}
