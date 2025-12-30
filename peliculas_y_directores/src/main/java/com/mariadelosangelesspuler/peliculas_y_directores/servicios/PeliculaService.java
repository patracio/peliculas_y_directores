package com.mariadelosangelesspuler.peliculas_y_directores.servicios;

import com.mariadelosangelesspuler.peliculas_y_directores.dto.PeliculaRequestDTO;
import com.mariadelosangelesspuler.peliculas_y_directores.dto.PeliculaResponseDTO;
import com.mariadelosangelesspuler.peliculas_y_directores.entidades.Director;
import com.mariadelosangelesspuler.peliculas_y_directores.entidades.Pelicula;
import com.mariadelosangelesspuler.peliculas_y_directores.repositorios.DirectorRepository;
import com.mariadelosangelesspuler.peliculas_y_directores.repositorios.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PeliculaService {
    
    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @Autowired
    private DirectorRepository directorRepository;
    
    public List<PeliculaResponseDTO> obtenerTodasLasPeliculas() {
        return peliculaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public PeliculaResponseDTO obtenerPeliculaPorNombre(String nombre) {
        Pelicula pelicula = peliculaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("La película no se encuentra en nuestra lista."));
        return convertirADTO(pelicula);
    }
    
    public List<PeliculaResponseDTO> obtenerPeliculasPorDirector(String nombreDirector) {
        List<Pelicula> peliculas = peliculaRepository.findByDirectorNombre(nombreDirector);
        if (peliculas.isEmpty()) {
            throw new RuntimeException("No contamos con películas con ese director en nuestra lista.");
        }
        return peliculas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public PeliculaResponseDTO crearPelicula(PeliculaRequestDTO requestDTO) {
        // Buscar o crear el director
        Director director = directorRepository.findByNombre(requestDTO.getNombreDirector())
                .orElseGet(() -> {
                    Director nuevoDirector = new Director(requestDTO.getNombreDirector());
                    return directorRepository.save(nuevoDirector);
                });
        
        // Crear la película
        Pelicula pelicula = new Pelicula(requestDTO.getNombre(), director);
        Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
        
        return convertirADTO(peliculaGuardada);
    }
    
    public PeliculaResponseDTO actualizarPelicula(Long id, PeliculaRequestDTO requestDTO) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con id: " + id));
        
        // Buscar o crear el director
        Director director = directorRepository.findByNombre(requestDTO.getNombreDirector())
                .orElseGet(() -> {
                    Director nuevoDirector = new Director(requestDTO.getNombreDirector());
                    return directorRepository.save(nuevoDirector);
                });
        
        pelicula.setNombre(requestDTO.getNombre());
        pelicula.setDirector(director);
        
        Pelicula peliculaActualizada = peliculaRepository.save(pelicula);
        return convertirADTO(peliculaActualizada);
    }
    
    public void eliminarPelicula(Long id) {
        if (!peliculaRepository.existsById(id)) {
            throw new RuntimeException("Película no encontrada con id: " + id);
        }
        peliculaRepository.deleteById(id);
    }
    
    private PeliculaResponseDTO convertirADTO(Pelicula pelicula) {
        return new PeliculaResponseDTO(
                pelicula.getId(),
                pelicula.getNombre(),
                pelicula.getDirector().getNombre(),
                pelicula.getDirector().getId()
        );
    }
}
