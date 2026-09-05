package com.universidad;

import com.cleandev.cli.config.CliConfig;
import com.cleandev.cli.core.CliEngine;
import com.cleandev.cli.core.SystemModule;
import com.universidad.config.ConfiguracionDependencia;
import java.util.List;


public class App {
    public static void main(String[] args) {
        try {
            iniciarAplicacion();
        } catch (Exception e) {
            System.err.println("ERROR: NO SE PUEDE INICIAR LA APLICACION");
            System.err.println(e.getMessage());
            System.exit(1);
            
        }
    }

    private static void iniciarAplicacion() {
        CliConfig cliConfig = AppScannerCli.configuracionCompleta();
        CliEngine motorConsola = new CliEngine(cliConfig);
        
        try {
            ConfiguracionDependencia config = new ConfiguracionDependencia();
            registroModulos(motorConsola, config);
            motorConsola.start();            
        } catch (Exception e) {
            System.out.println("Uyyy");
            System.out.println(e.getMessage());
        }
    }
    
    private static void registroModulos(CliEngine cliEngine, ConfiguracionDependencia config) {
        List<SystemModule> modulos = config.getModulos();
        for (SystemModule modulo : modulos) {
            cliEngine.registerModule(modulo);
        }
    }
}
