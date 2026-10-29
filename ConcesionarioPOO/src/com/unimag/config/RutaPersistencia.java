package com.unimag.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum RutaPersistencia {
    CLIENTES("CLIENTES.txt"),
    VEHICULOS("VEHICULOS.txt"),
    VENDEDORES("VENDEDORES.txt");
    
    private final Path ruta;

    RutaPersistencia(String nombreArchivo) {
        ruta = Paths.get(System.getProperty("user.dir"), "misPersistencias", nombreArchivo);
    }
    
    public Path obtenerRuta(){
        return ruta;
    }
}
