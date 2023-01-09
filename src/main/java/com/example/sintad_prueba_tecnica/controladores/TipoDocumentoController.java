package com.example.sintad_prueba_tecnica.controladores;

import com.example.sintad_prueba_tecnica.dto.TipoContribuyenteDTO;
import com.example.sintad_prueba_tecnica.dto.TipoDocumentoDTO;
import com.example.sintad_prueba_tecnica.modelos.TipoContribuyente;
import com.example.sintad_prueba_tecnica.modelos.TipoDocumento;
import com.example.sintad_prueba_tecnica.servicios.ITipoDocumentoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/tipo_documento")
public class TipoDocumentoController {

    private final ITipoDocumentoService tipoDocumentoService;
    private final ModelMapper modelMapper;


    public TipoDocumentoController(ITipoDocumentoService tipoDocumentoService, ModelMapper modelMapper) {
        this.tipoDocumentoService = tipoDocumentoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<TipoDocumentoDTO>> listar(){
        List<TipoDocumentoDTO> dtoList = tipoDocumentoService
                .findAll()
                .stream()
                .map(
                        tipoDocumento -> modelMapper.map(tipoDocumento, TipoDocumentoDTO.class)
                )
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoDocumentoDTO> listarPorId(
            @PathVariable(name = "id") Integer id
    ){
        TipoDocumento tipoDocumento = tipoDocumentoService.findById(id);
        return new ResponseEntity<>(
                modelMapper.map(
                        tipoDocumento, TipoDocumentoDTO.class
                )
                , HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<TipoDocumento> registrar(
            @RequestBody TipoDocumentoDTO tipoDocumentoRequest
    ){
        TipoDocumento tipoDocumento = modelMapper.map(tipoDocumentoRequest, TipoDocumento.class);
        return new ResponseEntity<>(
                tipoDocumentoService.save(tipoDocumento)
                , HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<TipoDocumento> actualizar(
            @RequestBody TipoDocumentoDTO tipoDocumentoRequest
    ){
        TipoDocumento tipoDocumento = modelMapper.map(tipoDocumentoRequest, TipoDocumento.class);
        return new ResponseEntity<>(
                tipoDocumentoService.update(tipoDocumento)
                , HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable("id") Integer id
    ){
        tipoDocumentoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
