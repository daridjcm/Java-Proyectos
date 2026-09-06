package com.biblioteca.modelo;

import com.biblioteca.modelo.convertidor.EstadoEntidadConverter;
import com.biblioteca.modelo.enumeracion.EstadoEntidad;
import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;

public class Libro {
    @TpaId
    private Long idLibro;
    private Long idAutor;
    private String tituloLibro;
    private String codigoISBNLibro;
    private Integer añoPublicacionLibro;
    private String generoLibro;
    private Integer cantidadLibro;

    @TpaConvert(converter = EstadoEntidadConverter.class)
    private EstadoEntidad estadoLibro;

    // Reflexion de otras librerias
    protected Libro() {}

    // Creación de libros
    public Libro(
            String tituloLibro,
            String codigoISBN_Libro,
            Integer añoPublicacionLibro,
            String generoLibro,
            Integer cantidadLibro) {

        this.tituloLibro = tituloLibro;
        this.codigoISBNLibro = codigoISBN_Libro;
        this.añoPublicacionLibro = añoPublicacionLibro;
        this.generoLibro = generoLibro;
        this.cantidadLibro = cantidadLibro;
        this.estadoLibro = EstadoEntidad.ACTIVO;
    }

    // Hidratación
    public Libro(
            Long idLibro,
            String tituloLibro,
            String codigoISBN_Libro,
            Integer añoPublicacionLibro,
            String generoLibro,
            Integer cantidadLibro,
            EstadoEntidad estadoLibro) {

        if (idLibro == null) {
            throw new IllegalArgumentException("ID Libro es obligatorio");
        }

        this.idLibro = idLibro;
        this.tituloLibro = tituloLibro;
        this.codigoISBNLibro = codigoISBN_Libro;
        this.añoPublicacionLibro = añoPublicacionLibro;
        this.generoLibro = generoLibro;
        this.cantidadLibro = cantidadLibro;
        this.estadoLibro = (estadoLibro != null)
                ? estadoLibro
                : EstadoEntidad.ACTIVO;
    }

    public void actualizarTitulo(String nuevoTitulo) {
        if (nuevoTitulo.equalsIgnoreCase(this.tituloLibro)) {
            throw new IllegalArgumentException(
                "El título del libro que desea cambiar ya existe");
        }

        this.tituloLibro = nuevoTitulo;
    }

    public void actualizarCodigoISBN(String nuevoCodigo) {
        if (nuevoCodigo.equalsIgnoreCase(this.codigoISBNLibro)) {
            throw new IllegalArgumentException(
                "El código ISBN que desea cambiar ya existe");
        }

        this.codigoISBNLibro = nuevoCodigo;
    }

    public void actualizarAñoPublicacion(Integer nuevoAño) {
        if (nuevoAño.equals(this.añoPublicacionLibro)) {
            throw new IllegalArgumentException(
                "El año de publicación que desea cambiar ya existe");
        }

        this.añoPublicacionLibro = nuevoAño;
    }

    public void actualizarGenero(String nuevoGenero) {
        if (nuevoGenero.equalsIgnoreCase(this.generoLibro)) {
            throw new IllegalArgumentException(
                "El género que desea cambiar ya existe");
        }

        this.generoLibro = nuevoGenero;
    }

    public void actualizarCantidad(Integer nuevaCantidad) {
        if (nuevaCantidad.equals(this.cantidadLibro)) {
            throw new IllegalArgumentException(
                "La cantidad que desea cambiar ya existe");
        }

        this.cantidadLibro = nuevaCantidad;
    }

    public void actualizarAutor(Long nuevoIdAutor) {
        if (nuevoIdAutor.equals(this.idAutor)) {
            throw new IllegalArgumentException(
            "El autor que desea asignar ya es el autor actual");
        }

        this.idAutor = nuevoIdAutor;
    }

    public void cambiarEstado(EstadoEntidad nuevoEstado) {
        this.estadoLibro = this.estadoLibro.cambiarEstado(nuevoEstado);
    }

    public boolean estaActivo() {
        return this.estadoLibro == EstadoEntidad.ACTIVO;
    }

    // Getters
    public Long getIdLibro() {
        return idLibro;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public String getCodigoISBNLibro() {
        return codigoISBNLibro;
    }

    public Integer getAñoPublicacionLibro() {
        return añoPublicacionLibro;
    }

    public String getGeneroLibro() {
        return generoLibro;
    }

    public Integer getCantidadLibro() {
        return cantidadLibro;
    }

    public EstadoEntidad getEstadoLibro() {
        return estadoLibro;
    }
}