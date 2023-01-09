package com.example.sintad_prueba_tecnica.controladores;

import com.example.sintad_prueba_tecnica.dto.EntidadDTO;
import com.example.sintad_prueba_tecnica.modelos.Entidad;
import com.example.sintad_prueba_tecnica.servicios.IEntidadService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/entidad")
public class EntidadController {

    private final IEntidadService entidadService;
    private final ModelMapper modelMapper;

    public EntidadController(IEntidadService entidadService, ModelMapper modelMapper) {
        this.entidadService = entidadService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<EntidadDTO>> listar(){
        List<EntidadDTO> list = entidadService
                .findAll()
                .stream()
                .map(
                    entidad -> modelMapper.map(entidad, EntidadDTO.class)
                )
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntidadDTO> listarPorId(
            @PathVariable("id") Integer id
    ){
        Entidad entidad = entidadService.findById(id);
        return new ResponseEntity<>(
                modelMapper.map(
                    entidad, EntidadDTO.class
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Entidad> registrar(
            @Valid @RequestBody EntidadDTO entidadDTO
    ){
        Entidad entidadMapper = modelMapper.map(entidadDTO, Entidad.class);
        return new ResponseEntity<>(entidadService.save(entidadMapper), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Entidad> actualizar(
            @RequestBody EntidadDTO entidadDTO
    ){
        Entidad entidadMapper = modelMapper.map(entidadDTO, Entidad.class);
        Entidad entidad = entidadService.update(entidadMapper);
        return new ResponseEntity<>(entidad, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable("id") Integer id
    ){
        entidadService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
