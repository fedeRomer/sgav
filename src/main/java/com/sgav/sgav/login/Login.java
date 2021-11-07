package com.sgav.sgav.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sgav.sgav.usuario.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "login")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "password")
    @NonNull
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioId;

    @Column(name = "logged_in")
    @NonNull
    private Boolean loggedIn;

}
