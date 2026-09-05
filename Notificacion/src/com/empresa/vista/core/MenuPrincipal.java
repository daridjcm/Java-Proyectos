package com.empresa.vista.core;

import com.empresa.vista.io.Menu;
import java.util.List;

public class MenuPrincipal {
    private final Menu menu;
    private final List<ModuloSistema> modulos;

    public MenuPrincipal(Menu menu, List<ModuloSistema> modulos) {
        this.menu = menu;
        this.modulos = modulos;
    }
    
    public void iniciar() {
        menu.mostrarTitulo("Dios mio que sirva!");
        for (ModuloSistema modulo : modulos) {
            menu.mostrarMensaje("Modulo " + modulo.obtenerNombre());
            modulo.ejecutar();
            System.out.println("");
        }
        menu.mostrarMensaje("Gracias, este fue mi primer proyecto completo");
    }
}
