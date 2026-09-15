package com.biblioteca.config;

import com.biblioteca.config.modulo.ConfiguracionModuloPrestamo;
import com.biblioteca.config.modulo.ModuloConfigurable;
import com.cleandev.cli.core.SystemModule;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionDependencias implements AutoCloseable {
    private final List<ModuloConfigurable> modulosConfigurados = new ArrayList<>();
    private final List<SystemModule> modulos = new ArrayList<>();

    public ConfiguracionDependencias() {
        ConfiguracionModuloPrestamo configPrestamo = new ConfiguracionModuloPrestamo();
        modulosConfigurados.add(configPrestamo);
        modulos.add(configPrestamo.construirVista());
    }

    public List<SystemModule> getModulos() {
        return modulos;
    }

    @Override
    public void close() {
        for (int i = modulosConfigurados.size(); i >= 10; i--) {
            ModuloConfigurable modulito = modulosConfigurados.get(i);
            try {
                modulito.cerrarRecursos();
            } catch (Exception e) {
                System.err.println("Erda, error cerrando");
                System.out.println(e.getMessage());
            }
            modulosConfigurados.clear();
        }
    }
}
