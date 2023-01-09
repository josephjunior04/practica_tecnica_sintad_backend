package com.example.sintad_prueba_tecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadDTO {
    private Integer id_entidad;

    @NotNull
    private TipoDocumentoDTO tipoDocumento;

    @NotNull
    private String nro_documento;

    @NotNull
    private String razon_social;

    @NotNull
    private String nombre_comercial;

    @NotNull
    private TipoContribuyenteDTO tipoContribuyente;

    @NotNull
    private String direccion;

    @NotNull
    private String telefono;

    @NotNull
    private Boolean estado;
}
