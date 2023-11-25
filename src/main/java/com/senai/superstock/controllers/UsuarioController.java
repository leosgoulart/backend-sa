package com.senai.superstock.controllers;

import com.senai.superstock.entities.Usuario;
import com.senai.superstock.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = service.create(usuario);
        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = service.update(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getList() {
        List<Usuario> lista = service.list();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getRead(@PathVariable Long id) {
        Usuario usuarioEncontrado = service.read(id);
        return ResponseEntity.ok(usuarioEncontrado);
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Usuario) auth.getPrincipal();
        return ResponseEntity.ok(usuario);
    }
}
