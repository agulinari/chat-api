package com.asapp.backend.challenge.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserEntity implements Serializable {

    private Integer id;
    private String username;
    private String password;

}
