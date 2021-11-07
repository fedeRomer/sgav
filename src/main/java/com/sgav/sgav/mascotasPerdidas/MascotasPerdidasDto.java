package com.sgav.sgav.mascotasPerdidas;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class MascotasPerdidasDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String titulo;

    private String detalle;
    private Boolean encontrado;
    private Integer idPropietario;

    private MultipartFile  foto;

}
