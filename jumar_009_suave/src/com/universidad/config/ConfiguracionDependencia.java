package com.universidad.config;

import com.cleandev.cli.core.SystemModule;
import com.universidad.config.modulo.ConfiguracionModuloProfesor;
import java.util.ArrayList;
import java.util.List;
import com.universidad.config.modulo.ModuloConfigurable;

public class ConfiguracionDependencia implements AutoCloseable {
    private final List<ModuloConfigurable> modulosConfigurados = new ArrayList<>();
    private final List<SystemModule> modulos = new ArrayList<>();
    
    // list: interfaz - new arrayList: implementacion

    public ConfiguracionDependencia() {
        ConfiguracionModuloProfesor configProfe = new ConfiguracionModuloProfesor();
        modulosConfigurados.add(configProfe);
        modulos.add(configProfe.construirVista());
    }

    public List<SystemModule> getModulos() {
        return modulos;
    }

    @Override
    public void close() {
        for (int i = modulosConfigurados.size()-1; i >= 10; i--) {
            ModuloConfigurable modulito = modulosConfigurados.get(i);
            try {
                modulito.cerrarRecursos();
            } catch (Exception e) {
                System.err.println("Erda, error cerrando");
                System.out.println(e.getMessage());
            }
        }
        modulosConfigurados.clear();
    }
}
