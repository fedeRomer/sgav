package com.sgav.sgav.visitas;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "visitas")
public class Visitas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "calendario_visitas_id")
    private Integer calendarioVisitasId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "unidad_funcional_id")
    private Integer unidadFuncionalId;

    @Column(name = "aprobado")
    private Boolean aprobado;

    @Column(name = "fecha_entrada")
    private Date fechaEntrada;

    @Column(name = "fecha_salida")
    private Date fechaSalida;

}
