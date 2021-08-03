package com.sgav.sgav.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "notification_multa")
public class NotificationMulta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "monto_total")
    private String montoTotal;

    @Column(name = "notification_multacol")
    private BigDecimal notificationMultacol;

    @Column(name = "unidad_funcional_id")
    private Integer unidadFuncionalId;

    @Column(name = "propietario_id")
    private Integer propietarioId;

    @Column(name = "foto")
    private byte[] foto;

}
