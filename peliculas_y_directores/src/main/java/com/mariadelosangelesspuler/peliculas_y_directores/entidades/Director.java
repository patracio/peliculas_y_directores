package com.mariadelosangelesspuler.peliculas_y_directores.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directores")
public class Director {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre del director es obligatorio")
    @Column(nullable = false, unique = true)
    private String nombre;
    
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pelicula> peliculas = new ArrayList<>();
    
    public Director() {
    }
    
    public Director(String nombre) {
        this.nombre = nombre;
    }
    
    // Getters y Setters
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
    
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }
    
    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
