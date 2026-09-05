package com.transportes.modelo;

import com.transportes.repositorio.Vehiculo;
        
public class Auto implements Vehiculo {
    private Integer idAuto;
    private String marcaAuto;
    private Byte capacidadPasajeros;
    private Byte numeroPuertas;
    private String tipoCombustible;
    private Float capacidadMaletero; // desde 200 hasta 1.029Litros
    private Boolean aireAcondicionado;
    private String tipoTransmision;
    private Byte rendimientoAuto;

    public Auto(Integer idAuto, String marcaAuto, Byte capacidadPasajeros, Byte numeroPuertas, String tipoCombustible, Float capacidadMaletero, Boolean aireAcondicionado, String tipoTransmision, Byte rendimientoAuto) {
        this.idAuto = idAuto;
        this.marcaAuto = marcaAuto;
        this.capacidadPasajeros = capacidadPasajeros;
        this.numeroPuertas = numeroPuertas;
        this.tipoCombustible = tipoCombustible;
        this.capacidadMaletero = capacidadMaletero;
        this.aireAcondicionado = aireAcondicionado;
        this.tipoTransmision = tipoTransmision;
        this.rendimientoAuto = rendimientoAuto;
    }
    
    

    @Override
    public float calcularCostoViaje(int distancia, float precioCombustible, byte rendimiento) {
        float costo = distancia * (precioCombustible / this.rendimientoAuto);
        System.out.println("Costo del viaje: " + costo);
        return costo;
    }

    @Override
    public float calcularConsumo(int distancia) {
        float consumo = (float) distancia / this.rendimientoAuto;
        System.out.println("Consumo del viaje: " + consumo);
        return consumo;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Clase Auto" +
            "\n\tID Auto: " + this.idAuto +
            "\n\tMarca de auto: " + this.marcaAuto +
            "\n\tCapacidad de pasajeros: " + this.capacidadPasajeros +
            "\n\tNumero de puertas: " + this.numeroPuertas +
            "\n\tTipo de combustible: " + this.tipoCombustible +
            "\n\tCapacidad del maletero: " + this.capacidadMaletero + "L" +
            "\n\tTiene aire acondicionado: " + this.aireAcondicionado +
            "\n\tTipo de transmision: " + this.tipoTransmision +
            "\n\tRendimiento de auto: " + this.rendimientoAuto + "Km/h"
        );   
    }

    @Override
    public Integer transportarPasajeros(Integer pasajeros) {
        if(this.capacidadPasajeros >= pasajeros) {
            byte puestosDisponibles = (byte) (this.capacidadPasajeros - pasajeros);
            System.out.println("\n--- VALIDANDO CAPACIDAD DE PASAJEROS ---");
            System.out.println("Tranportando " + pasajeros + " pasajeros");
            System.out.println("Puestos disponibles: " + puestosDisponibles);
        } else {
            System.out.println("No se puede transportar a los pasajeros, el limite es de " + this.capacidadPasajeros + " pasajeros");
        }
        return pasajeros;
    }
    
}
