package com.roiala.apilist.apilist.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roiala.apilist.apilist.Entidades.Usuarios;

public interface RepositorioUsuarios extends JpaRepository<Usuarios,Integer>{
    
}
