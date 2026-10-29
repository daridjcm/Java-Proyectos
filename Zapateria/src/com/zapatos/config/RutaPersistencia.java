package com.zapatos.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum RutaPersistencia {
    ZAPATOS("zapatos.txt"),
    MARCAS("marcas.txt"),
    COLORES("colores.txt");
    
    private final Path ruta;

    RutaPersistencia(String nombreArchivo) {
        ruta = Paths.get(System.getProperty("user.dir"), "misPersistencias", nombreArchivo);
    }

    public Path obtenerRuta() {
        return ruta;
    }
}
