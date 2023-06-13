package com.roiala.apilist.apilist.controllers;

import java.net.URI;
import java.util.Optional;

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

import com.roiala.apilist.apilist.Entidades.Categorias;
import com.roiala.apilist.apilist.Entidades.Listas;
import com.roiala.apilist.apilist.Entidades.Usuarios;
import com.roiala.apilist.apilist.Repositories.RepositorioCategorias;
import com.roiala.apilist.apilist.Repositories.RepositorioListas;
import com.roiala.apilist.apilist.Repositories.RepositorioUsuarios;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/listas")
public class ListaControlador {

    @Autowired
    private RepositorioUsuarios repositorioUsuarios;

    @Autowired
    private RepositorioListas repositorioListas;

    @Autowired
    private RepositorioCategorias repositorioCategorias;

    @GetMapping
    public ResponseEntity<Page<Listas>> listarListas(Pageable pageable) {
        return ResponseEntity.ok(repositorioListas.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listas> obtenerListaPorId(@PathVariable Integer id) {
        Optional<Listas> listaOpcional = repositorioListas.findById(id);
        if (!listaOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(listaOpcional.get());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Listas> insertarLista(@Valid @RequestBody Listas lista, @PathVariable Integer id) {
        Optional<Usuarios> usuarioOpcional = repositorioUsuarios.findById(id);
        if (!usuarioOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        lista.setCreadoPor(usuarioOpcional.get());
        Listas listaGuardada = repositorioListas.save(lista);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(listaGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(listaGuardada);
    }

    @PostMapping
    public ResponseEntity<Listas> insertarLista(@Valid @RequestBody Listas lista) {
        Optional<Usuarios> usuarioOpcional = repositorioUsuarios.findById(lista.getCreadoPor().getId());
        if (!usuarioOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        lista.setCreadoPor(usuarioOpcional.get());
        Listas listaGuardada = repositorioListas.save(lista);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(listaGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(listaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listas> editarLista(@PathVariable Integer id, @Valid @RequestBody Listas lista) {
        Optional<Usuarios> usuarioOpcional = repositorioUsuarios.findById(lista.getCreadoPor().getId());
        if (!usuarioOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Listas> listaOpcional = repositorioListas.findById(id);
        if (!listaOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        lista.setCreadoPor(usuarioOpcional.get());
        lista.setId(listaOpcional.get().getId());
        repositorioListas.save(lista);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Listas> eliminarLista(@PathVariable Integer id) {
        Optional<Listas> listaOpcional = repositorioListas.findById(id);
        if (!listaOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        repositorioListas.delete(listaOpcional.get());

        return ResponseEntity.noContent().build();
    }

}
