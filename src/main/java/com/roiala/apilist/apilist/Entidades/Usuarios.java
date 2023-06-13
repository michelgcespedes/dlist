package com.roiala.apilist.apilist.Entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
    private String apellidos;
    @NotNull
    private String correo;
    private Date fechaAlta;
    private Date fechaNacimiento;
    @OneToMany(mappedBy = "creadoPor", cascade = CascadeType.ALL)
    private Set<Listas> listas = new HashSet<>();

    public Usuarios() {
    }

    public Usuarios(int id, String nombre, String apellidos, String correo, Date fechaAlta, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaAlta = fechaAlta;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Set<Listas> getListas() {
        return this.listas;
    }

    public void setListas(Set<Listas> listas) {
        this.listas = listas;
        for (Listas lista: listas){
            lista.setCreadoPor(this);
        }
    }

    public Usuarios id(int id) {
        setId(id);
        return this;
    }

    public Usuarios nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Usuarios apellidos(String apellidos) {
        setApellidos(apellidos);
        return this;
    }

    public Usuarios correo(String correo) {
        setCorreo(correo);
        return this;
    }

    public Usuarios fechaAlta(Date fechaAlta) {
        setFechaAlta(fechaAlta);
        return this;
    }

    public Usuarios fechaNacimiento(Date fechaNacimiento) {
        setFechaNacimiento(fechaNacimiento);
        return this;
    }

    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuarios)) {
            return false;
        }
        Usuarios usuarios = (Usuarios) o;
        return id == usuarios.id && Objects.equals(nombre, usuarios.nombre)
                && Objects.equals(apellidos, usuarios.apellidos) && Objects.equals(correo, usuarios.correo)
                && Objects.equals(fechaAlta, usuarios.fechaAlta)
                && Objects.equals(fechaNacimiento, usuarios.fechaNacimiento) && Objects.equals(listas, usuarios.listas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, correo, fechaAlta, fechaNacimiento, listas);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nombre='" + getNombre() + "'" +
                ", apellidos='" + getApellidos() + "'" +
                ", correo='" + getCorreo() + "'" +
                ", fechaAlta='" + getFechaAlta() + "'" +
                ", fechaNacimiento='" + getFechaNacimiento() + "'" +
                ", listas='" + getListas() + "'" +
                "}";
    }

}
