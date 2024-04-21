package com.example.ldbc41.controllers;

import com.example.ldbc41.models.Usuarios;
import com.example.ldbc41.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuarios> agregarUsuario(@RequestBody Usuarios usuario) {
        Usuarios nuevoUsuario = usuarioService.agregarUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
}
