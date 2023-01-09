package com.example.sintad_prueba_tecnica.repositorio;

import com.example.sintad_prueba_tecnica.modelos.Usuario;

public interface IUsuarioRepo extends IGenericoRepo<Usuario, Integer>{

    Usuario findOneByUsername(String username);
}
