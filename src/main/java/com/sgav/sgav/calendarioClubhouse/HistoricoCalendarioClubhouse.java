package com.sgav.sgav.calendarioClubhouse;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "historico_calendario_clubhouse")
public class HistoricoCalendarioClubhouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "propietario_id")
    private Integer propietarioId;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "duracionhs")
    private Integer duracionhs;

    @Column(name = "tipo")
    private String tipo;

}
