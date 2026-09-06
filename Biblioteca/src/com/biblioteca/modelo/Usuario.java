package com.biblioteca.modelo;

import com.biblioteca.modelo.convertidor.EstadoEntidadConverter;
import com.biblioteca.modelo.enumeracion.EstadoEntidad;
import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;

public class Usuario {
    @TpaId
    private Long idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String documentoUsuario;
    private String telefonoUsuario;
    private String correoUsuario;
    @TpaConvert(converter = EstadoEntidadConverter.class)
    private EstadoEntidad estadoUsuario;
    
    protected Usuario() {}

    public Usuario(String nombreUsuario, String apellidoUsuario, String documentoUsuario, String telefonoUsuario, String correoUsuario, EstadoEntidad estadoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.correoUsuario = correoUsuario;
        this.estadoUsuario = estadoUsuario;
    }
    
    // Hidratación
    public Usuario(Long idUsuario, String nombreUsuario, String apellidoUsuario, String documentoUsuario, String telefonoUsuario, String correoUsuario, EstadoEntidad estadoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.correoUsuario = correoUsuario;
        this.estadoUsuario = estadoUsuario;
        
        if(idUsuario == null) {
            throw new IllegalArgumentException("ID Usuario es obligatorio");
        }
        this.idUsuario = idUsuario;
    }
    
  public void actualizarNombre(String nuevoNombre) {
        if(nuevoNombre.equalsIgnoreCase(this.nombreUsuario)) {
            throw new IllegalArgumentException("Los nombres deben ser diferentes");
        }
        this.nombreUsuario = nuevoNombre;
    }
    
    public void actualizarApellido(String nuevoApellido) {
        if(nuevoApellido.equalsIgnoreCase(this.apellidoUsuario)) {
            throw new IllegalArgumentException("Los apellidos deben ser diferentes");
        }
        this.apellidoUsuario = nuevoApellido;
    }
    
    public void actualizarDocumento(String nuevoDocumento) {
        if(nuevoDocumento.equalsIgnoreCase(this.documentoUsuario)) {
            throw new IllegalArgumentException("Los numeros de documentos deben ser diferentes");
        }
        this.documentoUsuario = nuevoDocumento;
    }
    
    public void actualizarTelefono(String nuevoTelefono) {
        if(nuevoTelefono.equalsIgnoreCase(this.telefonoUsuario)) {
            throw new IllegalArgumentException("Los telefonos deben ser diferentes");
        }
        this.telefonoUsuario = nuevoTelefono;
    }
    
    public void actualizarCorreo(String nuevoCorreo) {
        if(nuevoCorreo.equalsIgnoreCase(this.correoUsuario)) {
            throw new IllegalArgumentException("Los correos deben ser diferentes");
        }
        this.correoUsuario = nuevoCorreo;
    }
    
    public void cambiarEstado(EstadoEntidad nuevoEstado) {
        this.estadoUsuario = this.estadoUsuario.cambiarEstado(nuevoEstado);
    }
    
    public boolean estaActivo() {
        return this.estadoUsuario == EstadoEntidad.ACTIVO;
    }
    
    // Getters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public EstadoEntidad getEstadoUsuario() {
        return estadoUsuario;
    }
    
}
