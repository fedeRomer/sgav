package com.sgav.sgav.calendarioVisitas;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "calendario_visitas")
public class CalendarioVisitas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "duracionhs")
    private Integer duracionhs;

    @Column(name = "tipo")
    private String tipo;

}