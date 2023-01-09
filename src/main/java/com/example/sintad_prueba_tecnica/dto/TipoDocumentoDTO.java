package com.example.sintad_prueba_tecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumentoDTO {

    private Integer id_tipo_documento;

    @NotNull
    private String codigo;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    @NotNull
    private Boolean estado;
}
