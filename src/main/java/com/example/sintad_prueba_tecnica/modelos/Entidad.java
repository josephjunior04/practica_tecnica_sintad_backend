package com.example.sintad_prueba_tecnica.modelos;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Table(name = "tb_entidad")
public class Entidad {
    @Id
    @Column(length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_entidad;

    //Llave foranea
    @OneToOne
    @JoinColumn(name = "id_tipo_documento",
            nullable = false,
            foreignKey = @ForeignKey(name = "tb_entidad_ibfk_3"))
    private TipoDocumento tipo_documento;

    @Column(nullable = false, length = 25)
    private String nro_documento;

    @Column(nullable = false, length = 100)
    private String razon_social;

    @Column(nullable = false, length = 100)
    private String nombre_comercial;

    //Llave foranea
    @OneToOne
    @JoinColumn(name = "id_tipo_contribuyente",
            nullable = false,
            foreignKey = @ForeignKey(name = "tb_entidad_ibfk_2"))
    private TipoContribuyente tipo_contribuyente;

    @Column(nullable = false, length = 250)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String telefono;

    @Column(nullable = false)
    private Boolean estado;
}
