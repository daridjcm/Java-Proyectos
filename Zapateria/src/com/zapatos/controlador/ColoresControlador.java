package com.zapatos.controlador;

import com.zapatos.dto.colores.ColoresCrearDto;
import com.zapatos.dto.colores.ColoresDto;
import com.zapatos.servicio.ColoresServicio;
import java.util.List;

public class ColoresControlador {

    private final ColoresServicio servicio;

    public ColoresControlador(ColoresServicio servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("SERVICIO REQUERIDO");
        }

        this.servicio = servicio;
    }

    public ColoresDto registrar(ColoresCrearDto dto) {
        return servicio.registrar(dto);
    }

    public List<ColoresDto> listarColores() {
        return servicio.listarTodos();
    }

    public int contarColores() {
        return servicio.contarColores();
    }
}