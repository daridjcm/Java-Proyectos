package com.biblioteca.config.modulo;

import com.cleandev.cli.core.SystemModule;

public interface ModuloConfigurable {
    SystemModule construirVista();
    void cerrarRecursos();
}
