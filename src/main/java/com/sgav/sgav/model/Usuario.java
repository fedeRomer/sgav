package com.sgav.sgav.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
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
