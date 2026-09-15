package com.biblioteca;

import com.biblioteca.config.ConfiguracionDependencias;
import com.cleandev.cli.config.CliConfig;
import com.cleandev.cli.core.CliEngine;
import com.cleandev.cli.core.SystemModule;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            iniciarApp();
        } catch (Exception e) {
            System.err.println("ERROR: NO SE PUEDE INICIAR LA APLICACION");
            System.err.println(e.getMessage());
            System.exit(1);           
        }
    }

    private static void iniciarApp() {
        CliConfig cliConfig = AppScannerCli.configuracionCompleta();
        CliEngine motorConsola = new CliEngine(cliConfig);
        
        try {
            ConfiguracionDependencias config = new ConfiguracionDependencias();
            registroModulo(motorConsola, config);
            motorConsola.start();
        } catch (Exception e) {
            System.out.println("Uyyy");
            System.out.println(e.getMessage());
        }
    }

    private static void registroModulo(CliEngine motorConsola, ConfiguracionDependencias config) {
        List<SystemModule> modulos = config.getModulos();
        for (SystemModule modulo : modulos) {
            motorConsola.registerModule(modulo);
        }
    }
}
