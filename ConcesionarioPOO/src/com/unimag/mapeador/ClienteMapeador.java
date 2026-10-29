
package com.unimag.mapeador;

import com.unimag.dto.cliente.ClienteDto;
import com.unimag.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteMapeador implements Mapeador<Cliente, ClienteDto>{
    public ClienteMapeador() {
    }

    @Override
    public ClienteDto toDto(Cliente Entidad) {
        if (Entidad == null) {
            throw new IllegalArgumentException("Cliente requerido");
        }
        
        return new ClienteDto(
                Entidad.getIdCliente(),
                Entidad.getNombreCliente(),
                Entidad.getLimiteCredito(),
                Entidad.getCantidadCompras(),
                Entidad.getFechaRegistro()
        );
    }

    @Override
    public List<ClienteDto> toDtoList(List<Cliente> entidades) {
        if(entidades == null || entidades.isEmpty()){
            return List.of();
        }
        List<ClienteDto> resultado = new ArrayList<>(entidades.size());
        for (Cliente cliente : entidades) {
            resultado.add(toDto(cliente));
        }
        return resultado;
    }
}