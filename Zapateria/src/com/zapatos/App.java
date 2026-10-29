package com.zapatos;

import com.cleandev.cli.config.CliConfig;
import com.cleandev.cli.core.CliEngine;
import com.cleandev.cli.core.SystemModule;
import com.zapatos.config.ConfigDependencias;
import java.util.List;

public class App {

    public static void main(String[] args) {

        try (
            ConfigDependencias config = new ConfigDependencias()
        ) {

            CliConfig cliConfig = AppScannerCli.configuracionCompleta();
            CliEngine motorConsola = new CliEngine(cliConfig);
            List<SystemModule> modulos = config.getModulos();

            for (SystemModule modulo : modulos) {
                motorConsola.registerModule(modulo);
            }

            motorConsola.start();
        } catch (Exception e) {
            System.err.println("ERROR: No se puede iniciar la App");
            System.err.println(e.getMessage());
        }
    }
}