package com.biblioteca.modelo;

import com.biblioteca.modelo.enumeracion.EstadoPrestamo;
import com.cleandev.tpa.api.annotations.TpaId;

public class Prestamo {
        @TpaId
      
    private Long idPrestamo;
    private Long idUsuario;
    private Long idLibro;
    private String fechaPrestamo;
    private String fechaDevolucionEsperada;
    private String fechaDevolucionReal;
    private EstadoPrestamo estadoPrestamo;

    // Reflexion de otras librerias
    protected Prestamo() {
    }

    // Creación de préstamos
    public Prestamo(
            Long idUsuario,
            Long idLibro,
            String fechaPrestamo,
            String fechaDevolucionEsperada) {

        if (idUsuario == null) {
            throw new IllegalArgumentException("ID Usuario es obligatorio");
        }

        if (idLibro == null) {
            throw new IllegalArgumentException("ID Libro es obligatorio");
        }

        if (fechaPrestamo == null) {
            throw new IllegalArgumentException("La fecha del préstamo es obligatoria");
        }

        if (fechaDevolucionEsperada == null) {
            throw new IllegalArgumentException("La fecha de devolución esperada es obligatoria");
        }

        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.estadoPrestamo = EstadoPrestamo.PENDIENTE;
    }

    // Hidratación
    public Prestamo(
            Long idPrestamo,
            Long idUsuario,
            Long idLibro,
            String fechaPrestamo,
            String fechaDevolucionEsperada,
            String fechaDevolucionReal,
            EstadoPrestamo estadoPrestamo) {

        if (idPrestamo == null) {
            throw new IllegalArgumentException("ID Préstamo es obligatorio");
        }

        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estadoPrestamo = estadoPrestamo != null
                ? estadoPrestamo
                : EstadoPrestamo.PENDIENTE;
    }

    // Registrar devolución
    public void registrarDevolucion(String fechaDevolucion) {
        if (fechaDevolucion == null) {
            throw new IllegalArgumentException("La fecha de devolución es obligatoria");
        }

        this.fechaDevolucionReal = fechaDevolucion;
        this.estadoPrestamo = this.estadoPrestamo.cambiarEstado(EstadoPrestamo.DEVUELTO);
    }

    // Cambiar estado
    public void cambiarEstado(EstadoPrestamo nuevoEstado) {
        this.estadoPrestamo = this.estadoPrestamo.cambiarEstado(nuevoEstado);
    }

    public boolean estaPendiente() {
        return this.estadoPrestamo == EstadoPrestamo.PENDIENTE;
    }

    public boolean estaAtrasado() {
        return this.estadoPrestamo == EstadoPrestamo.ATRASADO;
    }

    public boolean estaDevuelto() {
        return this.estadoPrestamo == EstadoPrestamo.DEVUELTO;
    }

    // Getters
    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public String getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public String getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    // Setters
    public void setFechaDevolucionReal(String fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }
}
