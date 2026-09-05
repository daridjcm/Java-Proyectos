package com.universidad.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum RutaPersistencia {
    PROFESORES("profesores.txt"),
    ESTUDIANTES("estudiantes.txt"),
    FACULTADES("facultades.txt");
    
    private final Path ruta;

    RutaPersistencia(String nombreArchivo) {
        ruta = Paths.get(System.getProperty("user.dir"), "misPersistencias", nombreArchivo);
    }
    
    public Path obtenerRuta(){
        return ruta;
    }
}
