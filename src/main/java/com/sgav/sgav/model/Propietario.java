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
@Table(name = "propietario")
public class Propietario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "unidad_funcional_id")
    private Integer unidadFuncionalId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

}
