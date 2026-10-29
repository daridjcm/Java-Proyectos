package edu.unimag.modelo;

import edu.unimag.modelo.pk.CursoProfesorPk;
import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;
import edu.unimag.modelo.convertidor.TipoAsignacionConverter;
import edu.unimag.modelo.enumeracion.TipoAsignacion;
import edu.unimag.modelo.CursoProfesor;
import edu.unimag.modelo.convertidor.EstadoProfesorCursoConvertidor;
import edu.unimag.modelo.enumeracion.EstadoProfesorCurso;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CursoProfesor {
    @TpaId
    private CursoProfesorPk idCursoProfesorPeriodo;
    private OffsetDateTime fechaAsignacion;
    @TpaConvert(converter = TipoAsignacionConverter.class)
    private TipoAsignacion tipoAsignacion;
    @TpaConvert(converter = EstadoProfesorCursoConvertidor.class)
    private EstadoProfesorCurso estado;

    // Reflexión
    protected CursoProfesor() {
    }

    // Creación (se incluye id porque en las llaves cmpuestas no se genera automaticamente)
    public CursoProfesor(CursoProfesorPk idCursoProfesorPeriodo, TipoAsignacion tipoAsignacion) {
        this.idCursoProfesorPeriodo = idCursoProfesorPeriodo;
        this.tipoAsignacion = tipoAsignacion;
        fechaAsignacion = OffsetDateTime.now();
        estado = EstadoProfesorCurso.ASIGNADO;
    }
    
    // Hidratación
    public CursoProfesor(CursoProfesorPk idCursoProfesorPeriodo, OffsetDateTime fechaAsignacion, TipoAsignacion tipoAsignacion, EstadoProfesorCurso estado) {
        this.idCursoProfesorPeriodo = idCursoProfesorPeriodo;
        this.tipoAsignacion = tipoAsignacion;
        this.fechaAsignacion = fechaAsignacion;
        this.estado = estado;
    }
    
    public void cambiarTipoAsignacion(TipoAsignacion nuevo) {
        if(nuevo == tipoAsignacion) {
            throw new IllegalArgumentException("El tipo no es diferente");
        }
        tipoAsignacion = nuevo;
    }
    
    public void retirar() {
        estado = estado.cambiarEstado(EstadoProfesorCurso.RETIRADO);
    } 
    
    public void reAsignar() {
        estado = estado.cambiarEstado(EstadoProfesorCurso.ASIGNADO);
    }
    
    public boolean estaActivo() {
        return estado == EstadoProfesorCurso.ASIGNADO;
    }

    public CursoProfesorPk getIdCursoProfesorPeriodo() {
        return idCursoProfesorPeriodo;
    }

    public OffsetDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public TipoAsignacion getTipoAsignacion() {
        return tipoAsignacion;
    }

    public EstadoProfesorCurso getEstado() {
        return estado;
    }
    
    // Ley demeter
    public Long getIdProfesor() {
        return idCursoProfesorPeriodo.getIdProfesor();
    }
    
    public UUID getIdCursoPeriodo() {
        return idCursoProfesorPeriodo.getIdCursoPeriodo();
    }
    
    public Profesor getProfesor() {
        return idCursoProfesorPeriodo.getProfesor();
    }
    
    public CursoPeriodo getCursoPeriodo() {
        return idCursoProfesorPeriodo.getCursoPeriodo();
    }
}
