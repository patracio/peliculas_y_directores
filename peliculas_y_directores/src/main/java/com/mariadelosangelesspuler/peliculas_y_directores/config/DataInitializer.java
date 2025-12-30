package com.mariadelosangelesspuler.peliculas_y_directores.config;

import com.mariadelosangelesspuler.peliculas_y_directores.entidades.Director;
import com.mariadelosangelesspuler.peliculas_y_directores.entidades.Pelicula;
import com.mariadelosangelesspuler.peliculas_y_directores.repositorios.DirectorRepository;
import com.mariadelosangelesspuler.peliculas_y_directores.repositorios.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private DirectorRepository directorRepository;
    
    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Crear directores
        Director donHall = directorRepository.save(new Director("Don Hall"));
        Director tedBerman = directorRepository.save(new Director("Ted Berman"));
        Director kevinLima = directorRepository.save(new Director("Kevin Lima"));
        Director barryCook = directorRepository.save(new Director("Barry Cook"));
        
        // Crear películas
        peliculaRepository.save(new Pelicula("Winnie the Pooh", donHall));
        peliculaRepository.save(new Pelicula("El zorro y el sabueso", tedBerman));
        peliculaRepository.save(new Pelicula("Tarzán", kevinLima));
        peliculaRepository.save(new Pelicula("Mulán", barryCook));
        peliculaRepository.save(new Pelicula("Oliver", kevinLima));
        peliculaRepository.save(new Pelicula("Big Hero 6", donHall));
        
        System.out.println("Datos de ejemplo cargados en la base de datos H2");
    }
}
