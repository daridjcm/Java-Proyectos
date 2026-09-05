package com.empresa.config.modulo;

import com.empresa.vista.core.ModuloSistema;
import com.empresa.vista.io.Menu;

public interface ModuloConfigurable {
    ModuloSistema construirVista(Menu menu);
}
