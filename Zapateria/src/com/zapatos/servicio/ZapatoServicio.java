package com.zapatos.servicio;

import com.zapatos.dto.zapato.ZapatoActualizarDto;
import com.zapatos.dto.zapato.ZapatoCrearDto;
import com.zapatos.dto.zapato.ZapatoDto;
import com.zapatos.mapeador.ZapatoMapeador;
import com.zapatos.modelo.Colores;
import com.zapatos.modelo.Marca;
import com.zapatos.modelo.Zapato;
import com.zapatos.modelo.enumeracion.EstadoZapato;
import com.zapatos.repositorio.ColoresRepo;
import com.zapatos.repositorio.MarcaRepo;
import com.zapatos.repositorio.ZapatoRepo;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ZapatoServicio {

    private final ZapatoRepo repositorio;
    private final MarcaRepo marcaRepositorio;
    private final ColoresRepo colorRepositorio;
    private final ZapatoMapeador mapeador;

    public ZapatoServicio(
            ZapatoRepo repositorio,
            MarcaRepo marcaRepositorio,
            ColoresRepo colorRepositorio,
            ZapatoMapeador mapeador) {

        if (repositorio == null || marcaRepositorio == null || colorRepositorio == null || mapeador == null) {
            throw new IllegalArgumentException("Dependencias del servicio requeridas");
        }

        this.repositorio = repositorio;
        this.marcaRepositorio = marcaRepositorio;
        this.colorRepositorio = colorRepositorio;
        this.mapeador = mapeador;
    }

    public ZapatoDto registrar(ZapatoCrearDto dto) {
        Zapato zapato = new Zapato(
            dto.nombre(),
            EstadoZapato.DISPONIBLE,
            dto.marca(),
            dto.color(),
            dto.precio()
        );

        return mapeador.toDto(
            repositorio.guardar(zapato)
        );
    }

    public List<ZapatoDto> listarTodos() {
        return mapeador.toDtoList(
            repositorio.listarTodos()
        );
    }

    public int contarZapatos() {
        return (int) repositorio.contar();
    }

    public ZapatoDto buscarPorId(UUID id) {
        return repositorio.buscarPorId(id)
            .map(mapeador::toDto)
            .orElseThrow(()-> new IllegalArgumentException("No existe un zapato con ese ID"));
    }

    public ZapatoDto actualizar(ZapatoActualizarDto dto) {
        Zapato zapato = repositorio.buscarPorId(dto.id())
        .orElseThrow(()-> new IllegalArgumentException("No existe un zapato con ese ID"));

        if (dto.nuevoModelo() != null) {
            zapato.actualizarModelo(dto.nuevoModelo());
        }

        if (dto.nuevoPrecio() != null) {
            zapato.actualizarPrecio(dto.nuevoPrecio());
        }

        if (dto.nuevaMarca() != null) {
            zapato.cambiarMarca(dto.nuevaMarca());
        }

        if (dto.nuevoColor() != null) {
            zapato.cambiarColor(dto.nuevoColor());
        }

        if (dto.nuevoEstado() != null) {
            zapato.cambiarEstadoA(
                    dto.obtenerEstadoComoEnum()
            );
        }

        return mapeador.toDto(
                repositorio.actualizar(zapato)
        );
    }

    public List<ZapatoDto> listarPorMarca(String nombreMarca) {

        if (nombreMarca == null || nombreMarca.isBlank()) {
            throw new IllegalArgumentException("La marca es requerida");
        }

        return repositorio.listarTodos()
            .stream()
            .filter(z-> z.getMarcaZapato() != null && z.getMarcaZapato().getNombreMarca().equalsIgnoreCase(nombreMarca.trim()))
            .map(mapeador::toDto)
            .collect(Collectors.toList());
    }

    public List<ZapatoDto> listarPorColor(String nombreColor) {

        if (nombreColor == null || nombreColor.isBlank()) {
            throw new IllegalArgumentException("El color es requerido");
        }

        return repositorio.listarTodos()
            .stream()
            .filter(z-> z.getColorZapato() != null && z.getColorZapato().getNombreColor().equalsIgnoreCase(nombreColor.trim()))
            .map(mapeador::toDto)
            .collect(Collectors.toList());
    }

    public List<Marca> listarMarcas() {
        return marcaRepositorio.listarTodos();
    }

    public List<Colores> listarColores() {
        return colorRepositorio.listarTodos();
    }

    public Marca buscarMarca(UUID id) {
        return marcaRepositorio.buscarPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada"));
    }

    public Colores buscarColor(UUID id) {
        return colorRepositorio.buscarPorId(id)
        .orElseThrow(()-> new IllegalArgumentException("Color no encontrado"));
    }
}
