package com.unimag.config;

import com.cleandev.cli.core.SystemModule;
import com.unimag.config.modulo.ConfigModuloVehiculo;
import com.unimag.config.modulo.ConfigModuloCliente;
import com.unimag.config.modulo.ConfigModuloVendedor;
import java.util.ArrayList;
import java.util.List;
import com.unimag.config.modulo.ModuloConfigurable;

public class ConfigDependencias implements AutoCloseable {
    private final List<ModuloConfigurable> modulosConfigurados = new ArrayList<>();
    private final List<SystemModule> modulos = new ArrayList<>();
    
    // list: interfaz - new arrayList: implementacion

    public ConfigDependencias() {
        ConfigModuloCliente configCliente = new ConfigModuloCliente();
        modulosConfigurados.add(configCliente);
        modulos.add(configCliente.construirVista());
        
        ConfigModuloVehiculo configVehiculo = new ConfigModuloVehiculo();
        modulosConfigurados.add(configVehiculo);
        modulos.add(configVehiculo.construirVista());
        
        ConfigModuloVendedor configVendedor = new ConfigModuloVendedor();
        modulosConfigurados.add(configVendedor);
        modulos.add(configVendedor.construirVista());
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