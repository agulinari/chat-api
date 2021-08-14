package com.asapp.backend.challenge.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResource {

    private Integer id;
    private String username;
    private String password;

}
