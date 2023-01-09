package com.example.sintad_prueba_tecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoContribuyenteDTO {
    private Integer id_tipo_contribuyente;

    @NotNull
    private String nombre;

    @NotNull
    private Boolean estado;
}
