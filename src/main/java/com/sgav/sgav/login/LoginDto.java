package com.sgav.sgav.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    private String username;
    private String email;
    private String password;
    private Integer usuarioId;
    private Boolean loggedIn;

}
