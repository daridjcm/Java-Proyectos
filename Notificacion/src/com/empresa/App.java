package com.empresa;

import com.empresa.config.ConfiguracionDependencia;

public class App {
    public static void main(String[] args) {
        try {
            ConfiguracionDependencia config = new ConfiguracionDependencia();
            config.getMenuPrincipal().iniciar();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
