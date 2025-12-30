# API de Películas y Directores

API REST desarrollada con Spring Boot para la gestión de películas y directores, con base de datos H2 en memoria y documentación Swagger.

## 🚀 Características

- **Spring Boot 3.5.6** compatible con Java 1.8 y Java 17
- **Base de datos H2** en memoria
- **JPA/Hibernate** para persistencia de datos
- **Swagger/OpenAPI** para documentación interactiva
- **Arquitectura en capas** (Controladores, Servicios, Repositorios, DTOs, Entidades)
- **CRUD completo** para películas

## 📋 Requisitos

- Java 1.8 o superior (compatible hasta Java 17)
- Maven 3.6+

## 🔧 Instalación y Ejecución

### Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd peliculas_y_directores
```

### Compilar el proyecto
```bash
mvnw clean install
```

### Ejecutar la aplicación
```bash
mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📚 Endpoints Disponibles

### GET - Obtener todas las películas
```
GET /peliculas
```

### GET - Obtener película por nombre
```
GET /peliculas/{nombre}
```

### GET - Obtener películas por director
```
GET /peliculas/director/{nombre}
```

### POST - Crear nueva película
```
POST /peliculas
Content-Type: application/json

{
  "nombre": "Frozen",
  "nombreDirector": "Chris Buck"
}
```

### PUT - Actualizar película
```
PUT /peliculas/{id}
Content-Type: application/json

{
  "nombre": "Frozen II",
  "nombreDirector": "Chris Buck"
}
```

### DELETE - Eliminar película
```
DELETE /peliculas/{id}
```

## 📖 Documentación Swagger

Una vez ejecutada la aplicación, accede a:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## 🗄️ Consola H2

Para acceder a la consola de la base de datos H2:

- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: jdbc:h2:mem:peliculasdb
- **Usuario**: sa
- **Contraseña**: (dejar en blanco)

## 🏗️ Estructura del Proyecto

```
src/main/java/com/mariadelosangelesspuler/peliculas_y_directores/
├── config/
│   ├── SwaggerConfig.java
│   └── DataInitializer.java
├── controladores/
│   └── ControladorPeliculas.java
├── dto/
│   ├── PeliculaRequestDTO.java
│   └── PeliculaResponseDTO.java
├── entidades/
│   ├── Director.java
│   └── Pelicula.java
├── repositorios/
│   ├── DirectorRepository.java
│   └── PeliculaRepository.java
├── servicios/
│   └── PeliculaService.java
└── PeliculasYDirectoresApplication.java
```

## 🎬 Datos de Ejemplo

La aplicación se inicializa con los siguientes datos de ejemplo:

- Winnie the Pooh - Don Hall
- El zorro y el sabueso - Ted Berman
- Tarzán - Kevin Lima
- Mulán - Barry Cook
- Oliver - Kevin Lima
- Big Hero 6 - Don Hall

## 🛠️ Tecnologías Utilizadas

- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database
- SpringDoc OpenAPI (Swagger)
- Maven
- Java 1.8+

## 📝 Notas

- La base de datos H2 es en memoria, por lo que los datos se pierden al reiniciar la aplicación
- Para cambiar a una base de datos persistente, modifica `application.properties`
- El proyecto es compatible con Java 1.8 y Java 17

## 👨‍💻 Autor

María de los Ángeles Spuler
