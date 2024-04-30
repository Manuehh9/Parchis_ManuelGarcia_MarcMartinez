package com.Manuel_Garcia.gestiontopos.backend.business.model;

import java.io.Serializable;
import java.util.Date;

public class Topo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String descripcion;
    private Double peso;
    private String color;
    private Date fechaNacimiento;
    private boolean hambriento;

    public Topo(Long id, String nombre, String descripcion, Double peso, String color, Date fechaNacimiento, boolean hambriento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.color = color;
        this.fechaNacimiento = fechaNacimiento;
        this.hambriento = hambriento;
    }

    public Topo() {
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isHambriento() {
        return hambriento;
    }

    public void setHambriento(boolean hambriento) {
        this.hambriento = hambriento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Topo other = (Topo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Topo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", peso=" + peso
                + ", color=" + color + ", fechaNacimiento=" + fechaNacimiento + ", hambriento=" + hambriento + "]";
    }
}
