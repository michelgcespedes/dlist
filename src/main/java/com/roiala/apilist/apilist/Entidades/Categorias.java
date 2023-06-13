package com.roiala.apilist.apilist.Entidades;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    String nombre;
    String descripcion;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "listas_categorias", joinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id"), inverseJoinColumns = @JoinColumn(name = "lista_id", referencedColumnName = "lista_id"))
    private Set<Listas> listas = new HashSet<>();

    public Categorias() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Listas> getListas() {
        return this.listas;
    }

    public void setListas(Set<Listas> listas) {
        this.listas = listas;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nombre='" + getNombre() + "'" +
                ", descripcion='" + getDescripcion() + "'" +
                ", listas='" + getListas() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Categorias)) {
            return false;
        }
        Categorias categorias = (Categorias) o;
        return Objects.equals(id, categorias.id) && Objects.equals(nombre, categorias.nombre)
                && Objects.equals(descripcion, categorias.descripcion) && Objects.equals(listas, categorias.listas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, listas);
    }

}
