package com.sgav.sgav.notificationExpensa;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "notification_expensa")
public class NotificationExpensa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "monto_total")
    private BigDecimal montoTotal;

    @Column(name = "unidad_funcional_id")
    private Integer unidadFuncionalId;

    @Column(name = "propietario_id")
    private Integer propietarioId;

    @Column(name = "visto")
    private Boolean visto;

}
