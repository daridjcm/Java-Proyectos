package com.zapatos.config;

import com.cleandev.cli.core.SystemModule;
import com.zapatos.config.modulo.ConfigModuloZapato;
import com.zapatos.config.modulo.ModuloConfigurable;
import java.util.ArrayList;
import java.util.List;

public class ConfigDependencias implements AutoCloseable {

    private final List<ModuloConfigurable>
            modulosConfigurados = new ArrayList<>();

    private final List<SystemModule>
            modulos = new ArrayList<>();

    public ConfigDependencias() {
        ConfigModuloZapato modulo = new ConfigModuloZapato();

        modulosConfigurados.add(modulo);
        modulos.add(modulo.construirVista());
        modulos.add(modulo.construirVistaMarca());
        modulos.add(modulo.construirVistaColores());
    }

    public List<SystemModule> getModulos() {
        return modulos;
    }

    public List<ModuloConfigurable> getModulosConfigurados() {
        return modulosConfigurados;
    }

    @Override
    public void close() {
        for (int i = modulosConfigurados.size() - 1; i >= 0; i--) {
            try {
                modulosConfigurados.get(i).cerrarRecursos();
            } catch (Exception e) {
                System.err.println( "Error cerrando recursos: " + e.getMessage());
            }
        }
        modulosConfigurados.clear();
    }
}