package com.sgav.sgav.visitanteVehiculo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "visitante_vehiculo")
public class VisitanteVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "patente")
    private String patente;

    @Column(name = "fecha_vencimiento_poliza")
    private Date fechaVencimientoPoliza;


}
