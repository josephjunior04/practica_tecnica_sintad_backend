package com.example.sintad_prueba_tecnica.repositorio;

import com.example.sintad_prueba_tecnica.modelos.TipoDocumento;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TipoDocumentoRepoTests {

    @Autowired
    private ITipoDocumentoRepo tipoDocumentoRepo;

    private TipoDocumento tipoDocumento;

    @BeforeEach
    void setUp() {
        tipoDocumento = new TipoDocumento(null, "3435", "RUC", "NUEVO RUC", true);
    }

    @Test
    void testGuardarTipoDocumento(){
        TipoDocumento tipoDocumento = new TipoDocumento(
          null,
          "8765",
          "PASAPORTE",
          "NUEVO",
          true
        );

        TipoDocumento tipoDocumentoGuardado = tipoDocumentoRepo.save(tipoDocumento);

        assertThat(tipoDocumentoGuardado).isNotNull();
        assertThat(tipoDocumentoGuardado.getId_tipo_documento()).isGreaterThan(0);
    }

    @Test
    void testListarTipoDocumentos(){
        TipoDocumento tipoDocumentoTest = new TipoDocumento(null, "8798", "DNI", "DOCUMENTO", true);

        List<TipoDocumento> tipoDocumentoListAfter = tipoDocumentoRepo.findAll();

        tipoDocumentoRepo.save(tipoDocumentoTest);
        tipoDocumentoRepo.save(tipoDocumento);

        List<TipoDocumento> tipoDocumentoListBefore = tipoDocumentoRepo.findAll();

        assertThat(tipoDocumentoListBefore).isNotNull();
        assertThat(tipoDocumentoListBefore.size()).isEqualTo(tipoDocumentoListAfter.size() + 2);
    }

    @Test
    void testParaObtenerTipoDocumentoPorId(){
        tipoDocumentoRepo.save(tipoDocumento);

        TipoDocumento tipoDocumentoEncontrado = tipoDocumentoRepo.findById(tipoDocumento.getId_tipo_documento()).get();

        assertThat(tipoDocumentoEncontrado).isNotNull();
    }

    @Test
    void testParaEditarTipoDocumento(){
        tipoDocumentoRepo.save(tipoDocumento);

        TipoDocumento tipoDocumentoEncontrado = tipoDocumentoRepo.findById(tipoDocumento.getId_tipo_documento()).get();

        tipoDocumentoEncontrado.setNombre("DNI ACTUALIZADO");
        tipoDocumentoEncontrado.setDescripcion("DNI ACTULIZADO DESCRIPCION");

        TipoDocumento tipoDocumentoActulizado = tipoDocumentoRepo.save(tipoDocumentoEncontrado);

        assertThat(tipoDocumentoActulizado.getNombre()).isEqualTo("DNI ACTUALIZADO");
        assertThat(tipoDocumentoActulizado.getDescripcion()).isEqualTo("DNI ACTULIZADO DESCRIPCION");

    }

    @Test
    void testParaEliminarTipoDocumento(){
        tipoDocumentoRepo.save(tipoDocumento);

        tipoDocumentoRepo.deleteById(tipoDocumento.getId_tipo_documento());
        Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepo.findById(tipoDocumento.getId_tipo_documento());

        assertThat(optionalTipoDocumento).isEmpty();

    }
}
