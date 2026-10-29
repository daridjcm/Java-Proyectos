package com.zapatos.controlador;

import com.zapatos.dto.zapato.ZapatoActualizarDto;
import com.zapatos.dto.zapato.ZapatoCrearDto;
import com.zapatos.dto.zapato.ZapatoDto;
import com.zapatos.modelo.Colores;
import com.zapatos.modelo.Marca;
import com.zapatos.servicio.ZapatoServicio;
import java.util.List;
import java.util.UUID;

public class ZapatoControlador {

    private final ZapatoServicio servicio;

    public ZapatoControlador(ZapatoServicio servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("SERVICIO REQUERIDO");
        }

        this.servicio = servicio;
    }

    public ZapatoDto registrar(ZapatoCrearDto dto) {
        return servicio.registrar(dto);
    }

    public List<ZapatoDto> listarZapatos() {
        return servicio.listarTodos();
    }

    public int contarZapatos() {
        return servicio.contarZapatos();
    }

    public ZapatoDto buscarPorId(UUID id) {
        return servicio.buscarPorId(id);
    }

    public ZapatoDto actualizar(ZapatoActualizarDto dto) {
        return servicio.actualizar(dto);
    }

    public List<ZapatoDto> listarPorMarca(String marca) {
        return servicio.listarPorMarca(marca);
    }

    public List<ZapatoDto> listarPorColor(String color) {
        return servicio.listarPorColor(color);
    }

    public List<Marca> listarMarcas() {
        return servicio.listarMarcas();
    }

    public List<Colores> listarColores() {
        return servicio.listarColores();
    }

    public Marca buscarMarca(UUID id) {
        return servicio.buscarMarca(id);
    }

    public Colores buscarColor(UUID id) {
        return servicio.buscarColor(id);
    }
}