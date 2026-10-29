package com.zapatos.config.modulo;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.zapatos.config.RutaPersistencia;
import com.zapatos.controlador.ColoresControlador;
import com.zapatos.controlador.MarcaControlador;
import com.zapatos.controlador.ZapatoControlador;
import com.zapatos.mapeador.ColoresMapeador;
import com.zapatos.mapeador.MarcaMapeador;
import com.zapatos.mapeador.ZapatoMapeador;
import com.zapatos.modelo.Colores;
import com.zapatos.modelo.Marca;
import com.zapatos.modelo.Zapato;
import com.zapatos.persistencia.ColoresRepoImpl;
import com.zapatos.persistencia.MarcaRepoImpl;
import com.zapatos.persistencia.ZapatoRepoImpl;
import com.zapatos.repositorio.ColoresRepo;
import com.zapatos.repositorio.MarcaRepo;
import com.zapatos.repositorio.ZapatoRepo;
import com.zapatos.servicio.ColoresServicio;
import com.zapatos.servicio.MarcaServicio;
import com.zapatos.servicio.ZapatoServicio;
import com.zapatos.vista.ColoresVista;
import com.zapatos.vista.MarcaVista;
import com.zapatos.vista.ZapatoVista;
import java.util.UUID;

public class ConfigModuloZapato implements ModuloConfigurable {
    private final ZapatoRepo zapatoRepo;
    private final ZapatoServicio zapatoServicio;
    private final MarcaServicio marcaServicio;
    private final ColoresServicio coloresServicio;
    private final MarcaRepo marcaRepo;
    private final ColoresRepo coloresRepo;

    public ConfigModuloZapato() {
        TpaRepository<Zapato, UUID> tpaZapato = TpaRepositoryFactory.create(
                Zapato.class,
                RutaPersistencia.ZAPATOS.obtenerRuta(),
                true
        );
        TpaRepository<Marca, UUID> tpaMarca = TpaRepositoryFactory.create(
                Marca.class,
                RutaPersistencia.MARCAS.obtenerRuta(),
                true
        );
        TpaRepository<Colores, UUID> tpaColores = TpaRepositoryFactory.create(
                Colores.class,
                RutaPersistencia.COLORES.obtenerRuta(),
                true
        );

        zapatoRepo = new ZapatoRepoImpl(tpaZapato);
        marcaRepo = new MarcaRepoImpl(tpaMarca);
        coloresRepo = new ColoresRepoImpl(tpaColores);

        ZapatoMapeador mapeador = new ZapatoMapeador();
        zapatoServicio = new ZapatoServicio(zapatoRepo, marcaRepo, coloresRepo, mapeador);
        marcaServicio = new MarcaServicio(marcaRepo, new MarcaMapeador());
        coloresServicio = new ColoresServicio(coloresRepo, new ColoresMapeador());
    }

    public ZapatoRepo getZapatoRepo() {
        return zapatoRepo;
    }

    public ZapatoServicio getZapatoServicio() {
        return zapatoServicio;
    }

    public SystemModule construirVistaMarca() {
        return new MarcaVista(new MarcaControlador(marcaServicio));
    }

    public SystemModule construirVistaColores() {
        return new ColoresVista(new ColoresControlador(coloresServicio));
    }

    @Override
    public SystemModule construirVista() {
        ZapatoControlador controlador = new ZapatoControlador(zapatoServicio);
        return new ZapatoVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if (zapatoRepo != null) {
            zapatoRepo.cerrar();
        }

        if (marcaRepo != null) {
            marcaRepo.cerrar();
        }

        if (coloresRepo != null) {
            coloresRepo.cerrar();
        }
    }
}