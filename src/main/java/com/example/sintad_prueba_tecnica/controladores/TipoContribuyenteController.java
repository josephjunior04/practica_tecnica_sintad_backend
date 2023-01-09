package com.example.sintad_prueba_tecnica.controladores;

import com.example.sintad_prueba_tecnica.dto.EntidadDTO;
import com.example.sintad_prueba_tecnica.dto.TipoContribuyenteDTO;
import com.example.sintad_prueba_tecnica.modelos.Entidad;
import com.example.sintad_prueba_tecnica.modelos.TipoContribuyente;
import com.example.sintad_prueba_tecnica.servicios.ITipoContribuyenteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/tipo_contribuyente")
public class TipoContribuyenteController {

    private final ITipoContribuyenteService tipoContribuyenteService;
    private final ModelMapper modelMapper;


    public TipoContribuyenteController(ITipoContribuyenteService tipoContribuyenteService, ModelMapper modelMapper) {
        this.tipoContribuyenteService = tipoContribuyenteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<TipoContribuyenteDTO>> listar(){
        List<TipoContribuyenteDTO> dtoList = tipoContribuyenteService
                .findAll()
                .stream()
                .map(
                        tipoContribuyente -> modelMapper.map(tipoContribuyente, TipoContribuyenteDTO.class)
                )
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoContribuyenteDTO> listarPorId(
            @PathVariable("id") Integer id
    ){
        TipoContribuyente tipoContribuyente = tipoContribuyenteService.findById(id);
        return new ResponseEntity<>(
                modelMapper.map(
                        tipoContribuyente, TipoContribuyenteDTO.class
                ), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<TipoContribuyente> registrar(
            @RequestBody TipoContribuyenteDTO tipoContribuyenteRequest
    ){
        TipoContribuyente tipoContribuyente = modelMapper.map(tipoContribuyenteRequest, TipoContribuyente.class);
        return new ResponseEntity<>(
                tipoContribuyenteService.save(tipoContribuyente)
                , HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<TipoContribuyente> actualizar(
            @RequestBody TipoContribuyenteDTO tipoContribuyenteRequest
    ){
        TipoContribuyente tipoContribuyente = modelMapper.map(tipoContribuyenteRequest, TipoContribuyente.class);
        return new ResponseEntity<>(
                tipoContribuyenteService.update(tipoContribuyente)
                , HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable("id") Integer id
    ){
        tipoContribuyenteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
