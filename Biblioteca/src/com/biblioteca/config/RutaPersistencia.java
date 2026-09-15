package com.biblioteca.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum RutaPersistencia {
    PRESTAMOS("prestamos.txt"),
    AUTORES("autores.txt"),
    LIBROS("libros.txt"),
    USUARIOS("usuarios.txt");
    
    private final Path ruta;

    RutaPersistencia(String nombreArchivo) {
        ruta = Paths.get(System.getProperty("user.dir"), "misPersistencias", nombreArchivo);
    }
    
    public Path obtenerRuta() {
        return ruta;
    }
    
    
}
