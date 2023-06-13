package com.roiala.apilist.apilist.Entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "listas", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class Listas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private double puntuacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Usuarios creadoPor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "listas_categorias", joinColumns = @JoinColumn(name = "lista_id", referencedColumnName = "lista_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id"))
    private Set<Categorias> categorias = new HashSet<>();

    public Listas() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Usuarios getCreadoPor() {
        return this.creadoPor;
    }

    public void setCreadoPor(Usuarios creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Set<Categorias> getCategorias() {
        return this.categorias;
    }

    public void setCategorias(Set<Categorias> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nombre='" + getNombre() + "'" +
                ", descripcion='" + getDescripcion() + "'" +
                ", fechaCreacion='" + getFechaCreacion() + "'" +
                ", puntuacion='" + getPuntuacion() + "'" +
                ", creadoPor='" + getCreadoPor() + "'" +
                ", categorias='" + getCategorias() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Listas)) {
            return false;
        }
        Listas listas = (Listas) o;
        return id == listas.id && Objects.equals(nombre, listas.nombre)
                && Objects.equals(descripcion, listas.descripcion)
                && Objects.equals(fechaCreacion, listas.fechaCreacion) && puntuacion == listas.puntuacion
                && Objects.equals(creadoPor, listas.creadoPor) && Objects.equals(categorias, listas.categorias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, fechaCreacion, puntuacion, creadoPor, categorias);
    }

}
