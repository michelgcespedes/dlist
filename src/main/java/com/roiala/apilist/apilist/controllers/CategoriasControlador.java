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
import com.roiala.apilist.apilist.Repositories.RepositorioCategorias;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categorias")
public class CategoriasControlador {

    @Autowired
    private RepositorioCategorias repositorioCategorias;

    @GetMapping
    public ResponseEntity<Page<Categorias>> listarCategorias(Pageable pageable) {
        return ResponseEntity.ok(repositorioCategorias.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorias> obtenerCategoriaPorId(@PathVariable Integer id) {
        Optional<Categorias> categoriaOpcional = repositorioCategorias.findById(id);
        if (!categoriaOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(categoriaOpcional.get());
    }

    @PostMapping
    public ResponseEntity<Categorias> insertarCategoria(@Valid @RequestBody Categorias categoria) {
        Categorias categoriaGuardada = repositorioCategorias.save(categoria);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoriaGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(categoriaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorias> editarCategoria(@PathVariable Integer id, @RequestBody Categorias categoria) {
        Optional<Categorias> categoriaOpcional = repositorioCategorias.findById(id);
        if (!categoriaOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        repositorioCategorias.save(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categorias> eliminarCategoria(@PathVariable Integer id, @RequestBody Categorias categoria) {
        Optional<Categorias> categoriaOpcional = repositorioCategorias.findById(id);
        if (!categoriaOpcional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        repositorioCategorias.delete(categoriaOpcional.get());
        return ResponseEntity.noContent().build();
    }

}
