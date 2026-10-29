package com.zapatos.servicio;

import com.zapatos.dto.colores.ColoresCrearDto;
import com.zapatos.dto.colores.ColoresDto;
import com.zapatos.mapeador.ColoresMapeador;
import com.zapatos.modelo.Colores;
import com.zapatos.repositorio.ColoresRepo;
import java.util.List;

public class ColoresServicio {

    private final ColoresRepo repositorio;
    private final ColoresMapeador mapeador;

    public ColoresServicio(ColoresRepo repositorio, ColoresMapeador mapeador) {
        if (repositorio == null || mapeador == null) {
            throw new IllegalArgumentException("Dependencias del servicio requeridas");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public ColoresDto registrar(ColoresCrearDto dto) {
        boolean existe = repositorio.listarTodos()
            .stream()
            .anyMatch(c -> c.getNombreColor().equalsIgnoreCase(dto.colorcito()));
        if (existe) {
            throw new IllegalArgumentException("Ya existe un color con ese nombre");
        }
        return mapeador.toDto(repositorio.guardar(new Colores(dto.colorcito())));
    }

    public List<ColoresDto> listarTodos() {
        return mapeador.toDtoList(repositorio.listarTodos());
    }

    public int contarColores() {
        return (int) repositorio.contar();
    }
}