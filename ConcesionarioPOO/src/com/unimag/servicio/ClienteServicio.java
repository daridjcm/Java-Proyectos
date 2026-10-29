package com.unimag.servicio;

import com.unimag.dto.cliente.ClienteCrearDto;
import com.unimag.dto.cliente.ClienteDto;
import com.unimag.mapeador.ClienteMapeador;
import com.unimag.modelo.Cliente;
import com.unimag.repositorio.ClienteRepositorio;
import java.util.List;

public class ClienteServicio {
    private final ClienteRepositorio repositorio;
    private final ClienteMapeador mapeador;

    public ClienteServicio(ClienteRepositorio repositorio, ClienteMapeador mapeador) {
        if (repositorio == null) {
            throw new IllegalArgumentException("pila con el repo");
        }
        if (mapeador == null) {
            throw new IllegalArgumentException("pila con el mapeador");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public ClienteDto registrarCliente(ClienteCrearDto dto) {
        Cliente nuevoCliente = new Cliente(dto.nombre(), dto.limiteCredito(), dto.cantidadCompras(), dto.fechaRegistro());
        Cliente grabado = repositorio.guardar(nuevoCliente);
        return mapeador.toDto(grabado);
    }

    public List<ClienteDto> listarTodosClientes() {
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    
    public int contarClientes() {
        return (int) repositorio.contar();
    }
}