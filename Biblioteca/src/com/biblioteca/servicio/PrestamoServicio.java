package com.biblioteca.servicio;

import com.biblioteca.dto.prestamo.PrestamoCrearDto;
import com.biblioteca.dto.prestamo.PrestamoDevolucionDto;
import com.biblioteca.dto.prestamo.PrestamoDto;
import com.biblioteca.mapeador.PrestamoMapeador;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.modelo.enumeracion.EstadoPrestamo;
import com.biblioteca.repositorio.PrestamoRepositorio;
import com.biblioteca.vista.constante.ConfiguracionCli;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class PrestamoServicio {
    private final PrestamoRepositorio repositorio;
    private final PrestamoMapeador mapeador;
    LocalDate hoy = LocalDate.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern(ConfiguracionCli.FORMATO_FECHA_VISUAL);
    String fechaHoy = hoy.format(formato);
    public PrestamoServicio(PrestamoRepositorio repositorio, PrestamoMapeador mapeador) {
        if (repositorio == null || mapeador == null) {
            throw new IllegalArgumentException("El repositorio y el mapeador son obligatorios");
        }

        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public PrestamoDto registrarPrestamo(PrestamoCrearDto dto) {
        
        Prestamo nuevoPrestamo = new Prestamo(
                dto.idUsuario(),
                dto.idLibro(),
                fechaHoy,
                dto.fechaDevolucionEsperada()
        );

        Prestamo grabado = repositorio.guardar(nuevoPrestamo);
        return mapeador.toDto(grabado);
    }

    public List<PrestamoDto> listarTodosPrestamos() {
        return mapeador.toDoList(repositorio.listarTodos());
    }

    public int contarPrestamos() {
        return (int) repositorio.contar();
    }

    public PrestamoDto devolverPrestamo(PrestamoDevolucionDto dto) {
        Long idPrestamo = dto.idPrestamo();

        Optional<Prestamo> resultado = repositorio.buscarPorId(idPrestamo);

        if (resultado.isEmpty()) {
            throw new IllegalArgumentException(
                "No existe un prestamo con el ID: " + idPrestamo
            );
        }

        Prestamo prestamo = resultado.get();

        prestamo.setFechaDevolucionReal(fechaHoy);
        prestamo.cambiarEstado(EstadoPrestamo.DEVUELTO);
        Prestamo actualizado = repositorio.actualizar(prestamo);

        return mapeador.toDto(actualizado);
    }

    public Optional<PrestamoDto> buscarPorId(Long id) {
        return repositorio.buscarPorId(id).map(mapeador::toDto);
    }
}