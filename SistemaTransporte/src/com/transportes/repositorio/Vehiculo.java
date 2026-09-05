package com.transportes.repositorio;

public interface Vehiculo {

    float calcularCostoViaje(
        int distancia,
        float precioCombustible,
        byte rendimiento
    );

    float calcularConsumo(int distancia);

    void mostrarInfo();

    Integer transportarPasajeros(Integer pasajeros);
}