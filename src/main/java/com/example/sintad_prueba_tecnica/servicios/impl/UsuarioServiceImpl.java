package com.example.sintad_prueba_tecnica.servicios.impl;

import com.example.sintad_prueba_tecnica.modelos.Usuario;
import com.example.sintad_prueba_tecnica.repositorio.IUsuarioRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioServiceImpl implements UserDetailsService {

    private final IUsuarioRepo userRepo;

    public UsuarioServiceImpl(IUsuarioRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepo.findOneByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("User not exists &s", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                roles
        );
        return userDetails;
    }
}
