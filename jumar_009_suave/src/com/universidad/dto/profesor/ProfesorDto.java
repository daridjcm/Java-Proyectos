/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.universidad.dto.profesor;

import com.universidad.modelo.enumeracion.EstadoEntidad;

public record ProfesorDto(Long id, String nombre, String celular, EstadoEntidad estado, boolean activo) {
    
}
