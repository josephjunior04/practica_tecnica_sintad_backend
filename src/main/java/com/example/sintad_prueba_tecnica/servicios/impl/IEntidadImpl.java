package com.example.sintad_prueba_tecnica.servicios.impl;

import com.example.sintad_prueba_tecnica.exception.ModelNotFoundException;
import com.example.sintad_prueba_tecnica.modelos.Entidad;
import com.example.sintad_prueba_tecnica.repositorio.IEntidadRepo;
import com.example.sintad_prueba_tecnica.servicios.IEntidadService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IEntidadImpl implements IEntidadService {

    private final IEntidadRepo entidadRepo;
    private final ModelMapper modelMapper;

    public IEntidadImpl(IEntidadRepo entidadRepo, ModelMapper modelMapper) {
        this.entidadRepo = entidadRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Entidad save(Entidad entidad) {
        if(entidad.getId_entidad() != null){
            Optional<Entidad> optionalEntidad = entidadRepo.findById(entidad.getId_entidad());
            if(optionalEntidad.isPresent()){
                throw new ModelNotFoundException(
                        String.format("El registro con el id %s ya existe.", entidad.getId_entidad().toString())
                );
            }
        }
        entidadRepo.save(entidad);
        return entidad;
    }

    @Override
    public Entidad update(Entidad entidad) {
        Optional<Entidad> optionalEntidad = entidadRepo.findById(entidad.getId_entidad());
        if(!optionalEntidad.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", entidad.getId_entidad().toString())
            );
        }
        entidadRepo.save(entidad);
        return entidad;
    }

    @Override
    public Entidad findById(Integer id) {
        Optional<Entidad> optionalEntidad = entidadRepo.findById(id);
        if(!optionalEntidad.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", id)
            );
        }
        return optionalEntidad.get();
    }

    @Override
    public List<Entidad> findAll() {
        return entidadRepo.findAll();
    }

    @Override
    public void delete(Integer id) {
        Optional<Entidad> optionalEntidad = entidadRepo.findById(id);
        if(!optionalEntidad.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", id)
            );
        }
        entidadRepo.deleteById(id);
    }
}
