package com.unimag.servicio;

import com.unimag.dto.vendedor.VendedorCrearDto;
import com.unimag.dto.vendedor.VendedorDto;
import com.unimag.mapeador.VendedorMapeador;
import com.unimag.modelo.Vendedor;
import com.unimag.repositorio.VendedorRepositorio;
import java.util.List;

public class VendedorServicio {
    private final VendedorRepositorio repositorio;
    private final VendedorMapeador mapeador;

    public VendedorServicio(VendedorRepositorio repositorio, VendedorMapeador mapeador) {
        if (repositorio == null) {
            throw new IllegalArgumentException("pila con el repo");
        }
        if (mapeador == null) {
            throw new IllegalArgumentException("pila con el mapeador");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public VendedorDto registrarVendedor(VendedorCrearDto dto) {
        Vendedor nuevoVendedor = new Vendedor(dto.nombre(), dto.comision(), dto.ventasMes(), dto.fechaContratacion());
        Vendedor grabado = repositorio.guardar(nuevoVendedor);
        return mapeador.toDto(grabado);
    }

    public List<VendedorDto> listarTodosVendedors() {
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    
    public int contarVendedors() {
        return (int) repositorio.contar();
    }
}    
