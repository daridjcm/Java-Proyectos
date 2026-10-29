package com.zapatos.servicio;

import com.zapatos.dto.marca.MarcaCrearDto;
import com.zapatos.dto.marca.MarcaDto;
import com.zapatos.mapeador.MarcaMapeador;
import com.zapatos.modelo.Marca;
import com.zapatos.repositorio.MarcaRepo;
import java.util.List;

public class MarcaServicio {

    private final MarcaRepo repositorio;
    private final MarcaMapeador mapeador;

    public MarcaServicio(MarcaRepo repositorio, MarcaMapeador mapeador) {
        if (repositorio == null || mapeador == null) {
            throw new IllegalArgumentException("Dependencias del servicio requeridas");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public MarcaDto registrar(MarcaCrearDto dto) {
        boolean existe = repositorio.listarTodos()
            .stream()
            .anyMatch(m -> m.getNombreMarca().equalsIgnoreCase(dto.marca()));
        if (existe) {
            throw new IllegalArgumentException("Ya existe una marca con ese nombre");
        }
        return mapeador.toDto(repositorio.guardar(new Marca(dto.marca())));
    }

    public List<MarcaDto> listarTodos() {
        return mapeador.toDtoList(repositorio.listarTodos());
    }

    public int contarMarcas() {
        return (int) repositorio.contar();
    }
}