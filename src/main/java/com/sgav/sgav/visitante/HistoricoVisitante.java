package com.sgav.sgav.visitante;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "historico_visitante")
public class HistoricoVisitante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_entrada")
    private Date fechaEntrada;

    @Column(name = "fecha_salida")
    private Date fechaSalida;

    @Column(name = "dni")
    private Integer dni;

    @Column(name = "unidad_funcional_id")
    private Integer unidadFuncionalId;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "visitas_id")
    private Integer visitasId;

    @Column(name = "foto")
    private byte[] foto;

}
