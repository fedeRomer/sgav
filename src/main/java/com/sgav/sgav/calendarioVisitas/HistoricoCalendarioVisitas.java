package com.sgav.sgav.calendarioVisitas;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "historico_calendario_visitas")
public class HistoricoCalendarioVisitas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "visitas_id")
    private Integer visitasId;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "duracionhs")
    private Integer duracionhs;

    @Column(name = "tipo")
    private String tipo;

}
