package com.mariadelosangelesspuler.peliculas_y_directores.dto;

import javax.validation.constraints.NotBlank;

public class PeliculaRequestDTO {
    
    @NotBlank(message = "El nombre de la película es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El nombre del director es obligatorio")
    private String nombreDirector;
    
    public PeliculaRequestDTO() {
    }
    
    public PeliculaRequestDTO(String nombre, String nombreDirector) {
        this.nombre = nombre;
        this.nombreDirector = nombreDirector;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombreDirector() {
        return nombreDirector;
    }
    
    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }
}
