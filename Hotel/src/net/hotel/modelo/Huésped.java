package net.hotel.modelo;

import java.util.List;

public class Huésped {
    private Integer id;
    private String nombre;
    private String apellido;
    private Reserva reserva;
    private Short documento_identidad;
    private Short telefono;
    private String correo;
    private List<String> historialReservas;
            
    public Huésped(Integer id, String nombre, String apellido, Reserva reserva, Short documento_identidad, Short telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.reserva = reserva;
        this.documento_identidad = documento_identidad;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    
}
