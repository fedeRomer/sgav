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
@Table(name = "unidad_funcional")
public class UnidadFuncional implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "propietario_id")
    private Integer propietarioId;

    @Column(name = "numero_uf")
    private Integer numeroUf;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "ubicacion")
    private String ubicacion;

}
