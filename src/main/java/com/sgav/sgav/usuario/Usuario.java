package com.sgav.sgav.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "rol_id")
    private Integer rolId;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "dni")
    private Integer dni;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "foto")
    private byte[] foto;

}
