package com.zapatos.controlador;

import com.zapatos.dto.marca.MarcaCrearDto;
import com.zapatos.dto.marca.MarcaDto;
import com.zapatos.servicio.MarcaServicio;
import java.util.List;

public class MarcaControlador {

    private final MarcaServicio servicio;

    public MarcaControlador(MarcaServicio servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("SERVICIO REQUERIDO");
        }

        this.servicio = servicio;
    }

    public MarcaDto registrarMarca(MarcaCrearDto dto) {
        return servicio.registrar(dto);
    }

    public List<MarcaDto> listarMarcas() {
        return servicio.listarTodos();
    }

    public int contarMarcas() {
        return servicio.contarMarcas();
    }
}