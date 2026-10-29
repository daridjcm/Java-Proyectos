
package com.universidad.config.modulos;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.universidad.config.RutaPersistencia;
import com.universidad.controlador.ProfesorControlador;
import com.universidad.mapeador.ProfesorMapeador;
import com.universidad.modelo.Profesor;
import com.universidad.persistencia.ProfesorRepositorioImpl;
import com.universidad.repositorio.ProfesorRepositorio;
import com.universidad.servicio.ProfesorServicio;
import com.universidad.vista.ProfesorVista;

public class ConfiguracioModuloProfesor implements ModuloConfigurable{
    private final ProfesorRepositorio profesorRespositorio;
    private final ProfesorServicio profesorServicio;

    public ConfiguracioModuloProfesor() {
        TpaRepository<Profesor, Long>tpaEngine= TpaRepositoryFactory.create(Profesor.class, RutaPersistencia.PROFESORES.obtenerRuta(), false);
        profesorRespositorio=new ProfesorRepositorioImpl(tpaEngine);
        ProfesorMapeador profesorMapeador= new ProfesorMapeador();
        profesorServicio=new ProfesorServicio(profesorRespositorio, profesorMapeador);
    }

    public ProfesorRepositorio getProfesorRespositorio() {
        return profesorRespositorio;
    }

    public ProfesorServicio getProfesorServicio() {
        return profesorServicio;
    }
    
    @Override
    public SystemModule construirVista() {
        ProfesorControlador controlador= new ProfesorControlador(profesorServicio);
        return new ProfesorVista(controlador);
        
    }

    @Override
    public void cerrarRecursos() {
    if(profesorRespositorio!= null){
        profesorRespositorio.cerrar();
    }
    } 
    
}
