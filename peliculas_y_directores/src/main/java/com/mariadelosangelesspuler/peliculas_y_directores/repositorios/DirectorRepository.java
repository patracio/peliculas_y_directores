package com.mariadelosangelesspuler.peliculas_y_directores.repositorios;

import com.mariadelosangelesspuler.peliculas_y_directores.entidades.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
