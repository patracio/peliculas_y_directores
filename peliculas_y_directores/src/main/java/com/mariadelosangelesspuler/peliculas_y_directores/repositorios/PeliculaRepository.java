package com.mariadelosangelesspuler.peliculas_y_directores.repositorios;

import com.mariadelosangelesspuler.peliculas_y_directores.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    Optional<Pelicula> findByNombre(String nombre);
    List<Pelicula> findByDirectorNombre(String nombreDirector);
}
