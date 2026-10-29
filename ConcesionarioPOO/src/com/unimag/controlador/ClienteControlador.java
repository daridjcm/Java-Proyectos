package com.unimag.controlador;

import com.unimag.dto.cliente.ClienteCrearDto;
import com.unimag.dto.cliente.ClienteDto;
import com.unimag.servicio.ClienteServicio;
import java.util.List;

public class ClienteControlador {
    private final ClienteServicio servicio;

    public ClienteControlador(ClienteServicio servicio) {
        if(servicio == null) {
            throw new IllegalArgumentException("Servicio Cliente reventado");
        }
        this.servicio = servicio;
    }
        
    public ClienteDto registrarCliente(ClienteCrearDto dto) {
        return servicio.registrarCliente(dto);
    }
    
    public List<ClienteDto> listarClientes() {
        return servicio.listarTodosClientes();
    }
    
    public int contarClientes() {
        return servicio.contarClientes();
    }

}
