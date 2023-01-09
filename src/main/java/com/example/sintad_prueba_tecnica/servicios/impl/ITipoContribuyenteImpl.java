package com.example.sintad_prueba_tecnica.servicios.impl;

import com.example.sintad_prueba_tecnica.exception.ModelNotFoundException;
import com.example.sintad_prueba_tecnica.modelos.TipoContribuyente;
import com.example.sintad_prueba_tecnica.repositorio.ITipoContribuyenteRepo;
import com.example.sintad_prueba_tecnica.servicios.ITipoContribuyenteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ITipoContribuyenteImpl implements ITipoContribuyenteService {

    private final ITipoContribuyenteRepo tipoContribuyenteRepo;

    public ITipoContribuyenteImpl(ITipoContribuyenteRepo tipoContribuyenteRepo) {
        this.tipoContribuyenteRepo = tipoContribuyenteRepo;
    }

    @Override
    public TipoContribuyente save(TipoContribuyente tipoContribuyente) {
        if(tipoContribuyente.getId_tipo_contribuyente() != null){
            Optional<TipoContribuyente> optionalTipoContribuyente = tipoContribuyenteRepo
                    .findById(tipoContribuyente.getId_tipo_contribuyente());
            if(optionalTipoContribuyente.isPresent()){
                throw new ModelNotFoundException(
                        String.format("El registro con el id %s ya existe.", tipoContribuyente.getId_tipo_contribuyente().toString())
                );
            }
        }
        tipoContribuyenteRepo.save(tipoContribuyente);
        return tipoContribuyente;
    }

    @Override
    public TipoContribuyente update(TipoContribuyente tipoContribuyente) {
        Optional<TipoContribuyente> optionalTipoContribuyente = tipoContribuyenteRepo
                .findById(tipoContribuyente.getId_tipo_contribuyente());
        if(!optionalTipoContribuyente.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", tipoContribuyente.getId_tipo_contribuyente().toString())
            );
        }
        tipoContribuyenteRepo.save(tipoContribuyente);
        return tipoContribuyente;
    }

    @Override
    public TipoContribuyente findById(Integer id) {
        Optional<TipoContribuyente> optionalTipoContribuyente = tipoContribuyenteRepo
                .findById(id);
        if(!optionalTipoContribuyente.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", id)
            );
        }
        return optionalTipoContribuyente.get();
    }

    @Override
    public List<TipoContribuyente> findAll() {
        return tipoContribuyenteRepo.findAll();
    }

    @Override
    public void delete(Integer id) {
        Optional<TipoContribuyente> optionalTipoContribuyente = tipoContribuyenteRepo
                .findById(id);
        if(!optionalTipoContribuyente.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", id)
            );
        }
        tipoContribuyenteRepo.deleteById(id);
    }
}
