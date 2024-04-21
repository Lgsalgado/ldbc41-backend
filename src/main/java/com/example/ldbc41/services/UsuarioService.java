package com.example.ldbc41.services;

import com.example.ldbc41.models.Usuarios;
import com.example.ldbc41.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    public Usuarios agregarUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }
}
