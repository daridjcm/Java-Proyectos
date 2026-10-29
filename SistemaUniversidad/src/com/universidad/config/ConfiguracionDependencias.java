package com.universidad.config;

import com.cleandev.cli.core.SystemModule;
import com.universidad.config.modulos.ConfiguracioModuloProfesor;
import com.universidad.config.modulos.ConfiguracionModuloEstudiante;
import com.universidad.config.modulos.ConfiguracionModuloFacultad;
import com.universidad.config.modulos.ConfiguracionModuloMateria;
import com.universidad.config.modulos.ConfiguracionModuloPeriodoAcademico;
import com.universidad.config.modulos.ConfiguracionModuloSalon;
import com.universidad.config.modulos.ModuloConfigurable;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionDependencias implements AutoCloseable{
   private final List<ModuloConfigurable>modulosConfigurados=new ArrayList<>();
   private final List<SystemModule>modulos= new ArrayList<>();

    public ConfiguracionDependencias() {
        ConfiguracioModuloProfesor configProfe= new ConfiguracioModuloProfesor();
        modulosConfigurados.add(configProfe);
        modulos.add(configProfe.construirVista());
        ConfiguracionModuloEstudiante configEstudiante= new ConfiguracionModuloEstudiante();
        modulosConfigurados.add(configEstudiante);
        modulos.add(configEstudiante.construirVista());
        ConfiguracionModuloMateria configMateria= new ConfiguracionModuloMateria();
        modulosConfigurados.add(configMateria);
        modulos.add(configMateria.construirVista());
           ConfiguracionModuloSalon configSalon= new ConfiguracionModuloSalon();
        modulosConfigurados.add(configSalon);
        modulos.add(configSalon.construirVista());
          ConfiguracionModuloPeriodoAcademico configPeriodoAcademico= new ConfiguracionModuloPeriodoAcademico();
        modulosConfigurados.add(configPeriodoAcademico);
        modulos.add(configPeriodoAcademico.construirVista());
        ConfiguracionModuloFacultad configFacultad = new ConfiguracionModuloFacultad();
        modulosConfigurados.add(configFacultad);
        modulos.add(configFacultad.construirVista());
    }

    public List<SystemModule> getModulos() {
        return modulos;
    }

    public List<ModuloConfigurable> getModulosConfigurados() {
        return modulosConfigurados;
    }
    

    @Override
    public void close() {
        for (int i = modulosConfigurados.size()-1; i >=0; i--) {
           ModuloConfigurable modulo= modulosConfigurados.get(i);
            try {
               modulo.cerrarRecursos();
            } catch (Exception e) {
                System.err.println("Pilas!!! Cerraste mal");
                System.err.println(e.getMessage());
            }
        }
        
        modulosConfigurados.clear();
    }

  
   
   
}
