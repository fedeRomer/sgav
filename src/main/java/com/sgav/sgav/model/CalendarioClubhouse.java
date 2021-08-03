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
@Table(name = "calendario_clubhouse")
public class CalendarioClubhouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
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
