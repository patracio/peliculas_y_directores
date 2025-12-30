package com.mariadelosangelesspuler.peliculas_y_directores.controladores;

import com.mariadelosangelesspuler.peliculas_y_directores.dto.PeliculaRequestDTO;
import com.mariadelosangelesspuler.peliculas_y_directores.dto.PeliculaResponseDTO;
import com.mariadelosangelesspuler.peliculas_y_directores.servicios.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
@Tag(name = "Películas", description = "API para gestión de películas y directores")
public class ControladorPeliculas {
    
    @Autowired
    private PeliculaService peliculaService;
    
    @GetMapping
    @Operation(summary = "Obtener todas las películas", 
               description = "Retorna una lista con todas las películas disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de películas obtenida exitosamente")
    })
    public ResponseEntity<List<PeliculaResponseDTO>> obtenerTodasLasPeliculas() {
        List<PeliculaResponseDTO> peliculas = peliculaService.obtenerTodasLasPeliculas();
        return ResponseEntity.ok(peliculas);
    }
    
    @GetMapping("/{nombre}")
    @Operation(summary = "Obtener película por nombre", 
               description = "Busca y retorna una película específica por su nombre")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Película encontrada"),
        @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    public ResponseEntity<?> obtenerPeliculaPorNombre(
            @Parameter(description = "Nombre de la película a buscar", required = true)
            @PathVariable String nombre) {
        try {
            PeliculaResponseDTO pelicula = peliculaService.obtenerPeliculaPorNombre(nombre);
            return ResponseEntity.ok(pelicula);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/director/{nombre}")
    @Operation(summary = "Obtener películas por director", 
               description = "Retorna todas las películas dirigidas por un director específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Películas encontradas"),
        @ApiResponse(responseCode = "404", description = "No se encontraron películas del director")
    })
    public ResponseEntity<?> obtenerPeliculasPorDirector(
            @Parameter(description = "Nombre del director", required = true)
            @PathVariable String nombre) {
        try {
            List<PeliculaResponseDTO> peliculas = peliculaService.obtenerPeliculasPorDirector(nombre);
            return ResponseEntity.ok(peliculas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @PostMapping
    @Operation(summary = "Crear nueva película", 
               description = "Agrega una nueva película a la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Película creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<PeliculaResponseDTO> crearPelicula(
            @Parameter(description = "Datos de la película a crear", required = true)
            @Valid @RequestBody PeliculaRequestDTO requestDTO) {
        PeliculaResponseDTO peliculaCreada = peliculaService.crearPelicula(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaCreada);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar película", 
               description = "Actualiza los datos de una película existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Película actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Película no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<?> actualizarPelicula(
            @Parameter(description = "ID de la película a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos de la película", required = true)
            @Valid @RequestBody PeliculaRequestDTO requestDTO) {
        try {
            PeliculaResponseDTO peliculaActualizada = peliculaService.actualizarPelicula(id, requestDTO);
            return ResponseEntity.ok(peliculaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar película", 
               description = "Elimina una película de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Película eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    public ResponseEntity<?> eliminarPelicula(
            @Parameter(description = "ID de la película a eliminar", required = true)
            @PathVariable Long id) {
        try {
            peliculaService.eliminarPelicula(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
