package com.empresa.config;

import com.empresa.config.modulo.ConfiguracionModuloCorreo;
import com.empresa.config.modulo.ConfiguracionModuloSms;
import com.empresa.config.modulo.ConfiguracionModuloWhatsapp;
import com.empresa.vista.core.MenuPrincipal;
import com.empresa.vista.core.ModuloSistema;
import com.empresa.vista.io.Menu;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionDependencia {
    private final Menu menu;
    private final MenuPrincipal menuPrincipal;
    
    public ConfiguracionDependencia() {
        menu = new Menu();
        List<ModuloSistema> modulos = new ArrayList<>();
        
        // Config correo
        ConfiguracionModuloCorreo moduloCorreo = new ConfiguracionModuloCorreo();
        modulos.add(moduloCorreo.construirVista(menu));
        
        // Config sms
        ConfiguracionModuloSms moduloSms = new ConfiguracionModuloSms();
        modulos.add(moduloSms.construirVista(menu));
        
        // Config WhatsApp
        ConfiguracionModuloWhatsapp moduloWhatsapp = new ConfiguracionModuloWhatsapp();
        modulos.add(moduloWhatsapp.construirVista(menu));
        
        menuPrincipal = new MenuPrincipal(menu, modulos);
    }
    
    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }
}
