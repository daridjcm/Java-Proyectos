package com.universidad.servicio;

import com.universidad.dto.materia.MateriaCrearDto;
import com.universidad.dto.materia.MateriaDto;
import com.universidad.mapeador.MateriaMapeador;
import com.universidad.modelo.Materia;
import com.universidad.repositorio.MateriaRepositorio;
import java.util.List;

public class MateriaServicio {

    private final MateriaRepositorio repositorio;
    private final MateriaMapeador mapeador;

    public MateriaServicio(MateriaRepositorio repositorio, MateriaMapeador mapeador) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Oye la persistencia");
        }
        if (mapeador == null) {
            throw new IllegalArgumentException("Mapper totiado");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }
    public MateriaDto registrarMateria(MateriaCrearDto dto){
        Materia nuevaMateria=new Materia(dto.codigo(), dto.nombre(), dto.creditos(),dto.cupo());
        Materia guardado=repositorio.guardar(nuevaMateria);
        return mapeador.toDto(guardado);
    }
    public List<MateriaDto>listarTodos(){
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    public int contarMateria(){
        return (int)repositorio.contar();
    }
}
