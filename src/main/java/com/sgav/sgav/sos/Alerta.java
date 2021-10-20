package com.sgav.sgav.sos;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "alerta")
public class Alerta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "unidad_funcional")
    private String unidadFuncional;

    @Column(name = "informacion")
    private String informacion;

}
