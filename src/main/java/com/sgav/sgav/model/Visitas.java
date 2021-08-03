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
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "visitas")
public class Visitas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
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
