package com.transportes;

import com.transportes.modelo.Auto;

public class App {
    public static void main(String[] args) {        
        Auto toyota = new Auto(
            1,
            "Toyota",
            (byte) 5,
            (byte) 4,
            "Gasolina",
            470.0f,
            true,
            "Automática",
            (byte) 15
        );
        
        toyota.mostrarInfo();
        toyota.transportarPasajeros(3);
    }  
}
