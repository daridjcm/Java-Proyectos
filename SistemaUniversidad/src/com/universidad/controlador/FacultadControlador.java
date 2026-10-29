package com.universidad.controlador;

import com.universidad.dto.facultad.FacultadActualizarDto;
import com.universidad.dto.facultad.FacultadCrearDto;
import com.universidad.dto.facultad.FacultadDto;
import com.universidad.servicio.FacultadServicio;
import java.util.List;
import java.util.Optional;

public class FacultadControlador {

    private final FacultadServicio facultadServicio;

    public FacultadControlador(FacultadServicio facultadServicio) {
        if (facultadServicio == null) {
            throw new IllegalArgumentException("SERVICIO REVENTADO");
        }
        this.facultadServicio = facultadServicio;
    }

    public FacultadDto registrar(FacultadCrearDto dto) {
        return facultadServicio.registrarFacultad(dto);
    }

    public List<FacultadDto> listarFacultades() {
        return facultadServicio.listarTodos();
    }

    public int contarFacultades() {
        return facultadServicio.contarFacultades();
    }

    public FacultadDto actualizar(FacultadActualizarDto dto) {
        return facultadServicio.actualizarFacultad(dto);
    }

    public Optional<FacultadDto> buscarPorId(Long id) {
        return facultadServicio.buscarPorId(id);
    }
}
