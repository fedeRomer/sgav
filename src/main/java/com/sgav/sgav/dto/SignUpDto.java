package com.sgav.sgav.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {

    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String password;
}
