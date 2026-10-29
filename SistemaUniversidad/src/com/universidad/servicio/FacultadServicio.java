package com.universidad.servicio;

import com.universidad.dto.facultad.FacultadActualizarDto;
import com.universidad.dto.facultad.FacultadCrearDto;
import com.universidad.dto.facultad.FacultadDto;
import com.universidad.mapeador.FacultadMapeador;
import com.universidad.modelo.Facultad;
import com.universidad.repositorio.FacultadRepositorio;
import java.util.List;
import java.util.Optional;

public class FacultadServicio {

    private final FacultadRepositorio repositorio;
    private final FacultadMapeador mapeador;

    public FacultadServicio(FacultadRepositorio repositorio, FacultadMapeador mapeador) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Oye la persistencia");
        }
        if (mapeador == null) {
            throw new IllegalArgumentException("Mapper totiado");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public FacultadDto registrarFacultad(FacultadCrearDto dto) {
        Facultad nuevaFacultad = new Facultad(dto.nombre());
        Facultad guardada = repositorio.guardar(nuevaFacultad);
        return mapeador.toDto(guardada);
    }

    public List<FacultadDto> listarTodos() {
        return mapeador.toDtoList(repositorio.listarTodos());
    }

    public int contarFacultades() {
        return (int) repositorio.contar();
    }

    public FacultadDto actualizarFacultad(FacultadActualizarDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO de actualizacion requerido");
        }
        Facultad facultad = repositorio.buscarPorId(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("No se encontro la facultad con ID: " + dto.id()));
        if (dto.nuevoNombre() != null) {
            facultad.actualizarNombre(dto.nuevoNombre());
        }
        Facultad guardada = repositorio.actualizar(facultad);
        return mapeador.toDto(guardada);
    }

    public Optional<FacultadDto> buscarPorId(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return repositorio.buscarPorId(id).map(mapeador::toDto);
    }
}
