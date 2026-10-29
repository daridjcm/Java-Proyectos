package com.unimag.controlador;

import com.unimag.dto.vendedor.VendedorCrearDto;
import com.unimag.dto.vendedor.VendedorDto;
import com.unimag.servicio.VendedorServicio;
import java.util.List;

public class VendedorControlador {
    private final VendedorServicio servicio;

    public VendedorControlador(VendedorServicio servicio) {
        if(servicio == null) {
            throw new IllegalArgumentException("Servicio Vendedor reventado");
        }
        this.servicio = servicio;
    }
        
    public VendedorDto registrarVendedor(VendedorCrearDto dto) {
        return servicio.registrarVendedor(dto);
    }
    
    public List<VendedorDto> listarVendedors() {
        return servicio.listarTodosVendedors();
    }
    
    public int contarVendedors() {
        return servicio.contarVendedors();
    }

}
