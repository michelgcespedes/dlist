package com.roiala.apilist.apilist.controllers;

import java.net.URI;
import java.util.Optional;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.roiala.apilist.apilist.Entidades.Usuarios;
import com.roiala.apilist.apilist.Repositories.RepositorioUsuarios;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosControlador {

    @Autowired
    private RepositorioUsuarios repositorioUsuarios;

    @GetMapping
    public ResponseEntity<Page<Usuarios>> listarUsuarios(Pageable pageable) {
        return ResponseEntity.ok(repositorioUsuarios.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuarios> usuarioOpcional = repositorioUsuarios.findById(id);
        if (!usuarioOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(usuarioOpcional.get());
    }

    @PostMapping
    public ResponseEntity<Usuarios> insertarUsuario(@Valid @RequestBody Usuarios usuario) {
        Usuarios usuarioInsertado = repositorioUsuarios.save(usuario);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuarioInsertado.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(usuarioInsertado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> editarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuarios usuario) {
        Optional<Usuarios> usuarioOpcional = repositorioUsuarios.findById(id);
        if (!usuarioOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setId(usuarioOpcional.get().getId());
        repositorioUsuarios.save(usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuarios> eliminarUsuario(@PathVariable Integer id) {
        Optional<Usuarios> usuarioOpcional = repositorioUsuarios.findById(id);
        if (!usuarioOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        repositorioUsuarios.delete(usuarioOpcional.get());
        return ResponseEntity.noContent().build();
    }

 
}
