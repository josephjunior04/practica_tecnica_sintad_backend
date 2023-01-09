package com.example.sintad_prueba_tecnica.servicios.impl;

import com.example.sintad_prueba_tecnica.exception.ModelNotFoundException;
import com.example.sintad_prueba_tecnica.modelos.TipoDocumento;
import com.example.sintad_prueba_tecnica.repositorio.ITipoDocumentoRepo;
import com.example.sintad_prueba_tecnica.servicios.ITipoDocumentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ITipoDocumentoImpl implements ITipoDocumentoService {

    private final ITipoDocumentoRepo tipoDocumentoRepo;

    public ITipoDocumentoImpl(ITipoDocumentoRepo tipoDocumentoRepo) {
        this.tipoDocumentoRepo = tipoDocumentoRepo;
    }

    @Override
    public TipoDocumento save(TipoDocumento tipoDocumento) {
        if(tipoDocumento.getId_tipo_documento() != null){
            Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepo
                    .findById(tipoDocumento.getId_tipo_documento());
            if(optionalTipoDocumento.isPresent()){
                throw new ModelNotFoundException(
                        String.format("El registro con el id %s ya existe.", tipoDocumento.getId_tipo_documento().toString())
                );
            }
        }
        tipoDocumentoRepo.save(tipoDocumento);
        return tipoDocumento;
    }

    @Override
    public TipoDocumento update(TipoDocumento tipoDocumento) {
        Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepo
                .findById(tipoDocumento.getId_tipo_documento());
        if(!optionalTipoDocumento.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", tipoDocumento.getId_tipo_documento().toString())
            );
        }
        tipoDocumentoRepo.save(tipoDocumento);
        return tipoDocumento;
    }

    @Override
    public TipoDocumento findById(Integer id) {
        Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepo
                .findById(id);
        if(!optionalTipoDocumento.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", id)
            );
        }
        return optionalTipoDocumento.get();
    }

    @Override
    public List<TipoDocumento> findAll() {
        return tipoDocumentoRepo.findAll();
    }

    @Override
    public void delete(Integer id) {
        Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepo
                .findById(id);
        if(!optionalTipoDocumento.isPresent()){
            throw new ModelNotFoundException(
                    String.format("El registro con el id %s no existe.", id)
            );
        }
        tipoDocumentoRepo.deleteById(id);
    }
}
