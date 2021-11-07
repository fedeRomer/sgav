package com.sgav.sgav.mascotasPerdidas;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mascotas_perdidas")
public class MascotasPerdidas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "detalle")
    private String detalle;
    @Column(name = "encontrado")
    private Boolean encontrado;
    @Column(name = "id_propietario")
    private Integer idPropietario;

    //se guarda ruta absoluta
    @Column(name = "foto")
    private String foto;

}
