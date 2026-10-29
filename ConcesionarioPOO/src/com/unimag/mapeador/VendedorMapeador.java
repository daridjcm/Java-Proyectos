package com.unimag.mapeador;

import com.unimag.dto.vendedor.VendedorDto;
import com.unimag.modelo.Vendedor;
import java.util.ArrayList;
import java.util.List;

public class VendedorMapeador implements Mapeador<Vendedor, VendedorDto>{
    public VendedorMapeador() {
    }

    @Override
    public VendedorDto toDto(Vendedor Entidad) {
        if (Entidad == null) {
            throw new IllegalArgumentException("Vendedor requerido");
        }
        
        return new VendedorDto(
                Entidad.getIdVendedor(),
                Entidad.getNombreVendedor(),
                Entidad.getComision(),
                Entidad.getVentasMes(),
                Entidad.getFechaContratacion()
        );
    }

    @Override
    public List<VendedorDto> toDtoList(List<Vendedor> entidades) {
        if(entidades == null || entidades.isEmpty()){
            return List.of();
        }
        List<VendedorDto> resultado = new ArrayList<>(entidades.size());
        for (Vendedor vendedor : entidades) {
            resultado.add(toDto(vendedor));
        }
        return resultado;
    }
}
