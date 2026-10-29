package com.unimag.servicio;

import com.unimag.dto.vehiculo.VehiculoCrearDto;
import com.unimag.dto.vehiculo.VehiculoDto;
import com.unimag.mapeador.VehiculoMapeador;
import com.unimag.modelo.Vehiculo;
import com.unimag.repositorio.VehiculoRepositorio;
import java.util.List;

public class VehiculoServicio {
    private final VehiculoRepositorio repositorio;
    private final VehiculoMapeador mapeador;

    public VehiculoServicio(VehiculoRepositorio repositorio, VehiculoMapeador mapeador) {
        if (repositorio == null) {
            throw new IllegalArgumentException("pila con el repo");
        }
        if (mapeador == null) {
            throw new IllegalArgumentException("pila con el mapeador");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public VehiculoDto registrarVehiculo(VehiculoCrearDto dto) {
        Vehiculo nuevoVehiculo = new Vehiculo(dto.marca(), dto.precio(), dto.año(), dto.fechaIngreso());
        Vehiculo grabado = repositorio.guardar(nuevoVehiculo);
        return mapeador.toDto(grabado);
    }

    public List<VehiculoDto> listarTodosVehiculos() {
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    
    public int contarVehiculos() {
        return (int) repositorio.contar();
    }
}    
