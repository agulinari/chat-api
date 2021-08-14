package com.asapp.backend.challenge.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResource {

    private Integer id;
    private String token;

}
