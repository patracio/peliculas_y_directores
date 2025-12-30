package com.mariadelosangelesspuler.peliculas_y_directores.dto;

public class PeliculaResponseDTO {
    
    private Long id;
    private String nombre;
    private String nombreDirector;
    private Long directorId;
    
    public PeliculaResponseDTO() {
    }
    
    public PeliculaResponseDTO(Long id, String nombre, String nombreDirector, Long directorId) {
        this.id = id;
        this.nombre = nombre;
        this.nombreDirector = nombreDirector;
        this.directorId = directorId;
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
    
    public String getNombreDirector() {
        return nombreDirector;
    }
    
    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }
    
    public Long getDirectorId() {
        return directorId;
    }
    
    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
}
