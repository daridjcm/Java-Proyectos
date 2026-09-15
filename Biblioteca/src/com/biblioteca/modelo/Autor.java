package com.biblioteca.modelo;

import com.biblioteca.modelo.convertidor.EstadoEntidadConverter;
import com.biblioteca.modelo.enumeracion.EstadoEntidad;
import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Autor {
    @TpaId
    private Long idAutor;
    private String nombreAutor;
    private String apellidoAutor;
    private String nacionalidadAutor;
    private String fechaNacimientoAutor;
    @TpaConvert(converter = EstadoEntidadConverter.class)
    private EstadoEntidad estadoAutor;
    Locale locale = Locale.of("es", "CO");

    // Reflexión de otras librerias
    protected Autor() {}

    // Creación
    public Autor(String nombreAutor, String apellidoAutor, String nacionalidadAutor, LocalDate fechaNacimientoAutor, EstadoEntidad estadoAutor) {
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.nacionalidadAutor = nacionalidadAutor;
        this.fechaNacimientoAutor = fechaNacimientoAutor.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", this.locale));
        this.estadoAutor = estadoAutor;
    }

    // Hidratación
    public Autor(Long idAutor, String nombreAutor, String apellidoAutor, String nacionalidadAutor, String fechaNacimientoAutor, EstadoEntidad estadoAutor) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.nacionalidadAutor = nacionalidadAutor;
        this.fechaNacimientoAutor = fechaNacimientoAutor;
        this.estadoAutor = estadoAutor;
        
        if(idAutor == null) {
            throw new IllegalArgumentException("ID Autor es obligatorio");
        }
        this.idAutor = idAutor;
    }
    
    public void actualizarNombre(String nuevoNombre) {
        if(nuevoNombre.equalsIgnoreCase(this.nombreAutor)) {
            throw new IllegalArgumentException("Los nombres deben ser diferentes");
        }
        this.nombreAutor = nuevoNombre;
    }
    
    public void actualizarApellido(String nuevoApellido) {
        if(nuevoApellido.equalsIgnoreCase(this.apellidoAutor)) {
            throw new IllegalArgumentException("Los apellidos deben ser diferentes");
        }
        this.apellidoAutor = nuevoApellido;
    }
    
    public void actualizarNacionalidad(String nuevaNacionalidad) {
        if(nuevaNacionalidad.equalsIgnoreCase(this.nacionalidadAutor)) {
            throw new IllegalArgumentException("Las nacionalidades deben ser diferentes");
        }
        this.nacionalidadAutor = nuevaNacionalidad;
    }
    
    public void actualizarFechaNacimiento(String nuevaFecha) {
        if(nuevaFecha.equals(this.fechaNacimientoAutor)) {
            throw new IllegalArgumentException("Las fechas de nacimiento deben ser diferentes");
        }
        this.fechaNacimientoAutor = nuevaFecha;
    }
    
    public void cambiarEstado(EstadoEntidad nuevoEstado) {
        this.estadoAutor = this.estadoAutor.cambiarEstado(nuevoEstado);
    }
    
    public boolean estaActivo() {
        return this.estadoAutor == EstadoEntidad.ACTIVO;
    }
    
    // Getters
    public Long getIdAutor() {
        return idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public String getNacionalidadAutor() {
        return nacionalidadAutor;
    }

    public String getFechaNacimientoAutor() {
        return fechaNacimientoAutor;
    }

    public EstadoEntidad getEstadoAutor() {
        return estadoAutor;
    }
}
