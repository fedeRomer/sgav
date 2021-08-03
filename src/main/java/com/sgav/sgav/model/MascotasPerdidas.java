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
@Table(name = "mascotas_perdidas")
public class MascotasPerdidas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "foto")
    private byte[] foto;

}
