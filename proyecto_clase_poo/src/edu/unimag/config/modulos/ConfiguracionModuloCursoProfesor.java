package edu.unimag.config.modulos;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import edu.unimag.config.RutaPersistencia;
import edu.unimag.config.modulos.api.ModuloConfigurable;
import edu.unimag.controlador.CursoProfesorControlador;
import edu.unimag.mapeador.*;
import edu.unimag.modelo.CursoProfesor;
import edu.unimag.modelo.pk.CursoProfesorPk;
import edu.unimag.persistencia.CursoProfesorRepositorioImpl;
import edu.unimag.repositorio.*;
import edu.unimag.servicio.CursoPeriodoServicio;
import edu.unimag.servicio.CursoProfesorServicio;
import edu.unimag.servicio.ProfesorServicio;
import edu.unimag.vista.CursoProfesorVista;

public class ConfiguracionModuloCursoProfesor implements ModuloConfigurable {
    private final CursoProfesorRepositorio cursoProfesorRepositorio;

    private final ProfesorRepositorio profesorRepositorio;
    private final ProfesorServicio profesorServicio;

    private final CursoPeriodoRepositorio cursoPeriodoRepositorio;
    private final CursoPeriodoServicio cursoPeriodoServicio;

    private final MateriaRepositorio materiaRepositorio;
    private final PeriodoAcademicoRepositorio periodoAcademicoRepositorio;

    public ConfiguracionModuloCursoProfesor(ProfesorRepositorio profesorRepositorio, ProfesorServicio profesorServicio, CursoPeriodoRepositorio cursoPeriodoRepositorio, CursoPeriodoServicio cursoPeriodoServicio, MateriaRepositorio materiaRepositorio, PeriodoAcademicoRepositorio periodoAcademicoRepositorio) {
        if (profesorRepositorio == null) {
            throw new IllegalArgumentException("Uy, profesor repositorio nulo");
        }
        this.profesorRepositorio = profesorRepositorio;
        if (profesorServicio == null) {
            throw new IllegalArgumentException("Uy, profesor servicio nulo");
        }
        this.profesorServicio = profesorServicio;
        if (cursoPeriodoRepositorio == null) {
            throw new IllegalArgumentException("Uy, curso periodo repositorio nulo");
        }
        this.cursoPeriodoRepositorio = cursoPeriodoRepositorio;
        if (cursoPeriodoServicio == null) {
            throw new IllegalArgumentException("Uy, curso periodo servicio nulo");
        }
        this.cursoPeriodoServicio = cursoPeriodoServicio;
        if (materiaRepositorio == null) {
            throw new IllegalArgumentException("Uy, materia repositorio nulo");
        }
        this.materiaRepositorio = materiaRepositorio;
        if (periodoAcademicoRepositorio == null) {
            throw new IllegalArgumentException("Uy, periodo academico repositorio nulo");
        }
        this.periodoAcademicoRepositorio = periodoAcademicoRepositorio;

        boolean necesitaUUID = false;
        TpaRepository<CursoProfesor, CursoProfesorPk> tpaEngine = TpaRepositoryFactory.create(
                CursoProfesor.class, RutaPersistencia.CURSOS_PROFESORES.obtenerRuta(), necesitaUUID
        );
        cursoProfesorRepositorio = new CursoProfesorRepositorioImpl(tpaEngine);
    }

    @Override
    public SystemModule construirVista() {
        ProfesorMapeador profesorMapeador = new ProfesorMapeador();
        MateriaMapeador materiaMapeador = new MateriaMapeador();
        PeriodoAcademicoMapeador periodoAcademicoMapeador = new PeriodoAcademicoMapeador();
        CursoPeriodoMapeador cursoPeriodoMapeador = new CursoPeriodoMapeador(materiaMapeador, periodoAcademicoMapeador);
        CursoProfesorMapeador cursoProfesorMapeador = new CursoProfesorMapeador(profesorMapeador, cursoPeriodoMapeador);
        CursoProfesorServicio cursoProfesorServicio = new CursoProfesorServicio(
                cursoProfesorRepositorio, profesorRepositorio, cursoPeriodoRepositorio, materiaRepositorio, periodoAcademicoRepositorio, cursoProfesorMapeador
        );

        CursoProfesorControlador controlador = new CursoProfesorControlador(cursoProfesorServicio, profesorServicio, cursoPeriodoServicio);
        return new CursoProfesorVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if(cursoProfesorRepositorio != null) {
            cursoProfesorRepositorio.cerrar();
        }
    }
}
