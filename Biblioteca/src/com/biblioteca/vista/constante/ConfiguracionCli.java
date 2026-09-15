package com.biblioteca.vista.constante;

import java.util.Locale;

public class ConfiguracionCli {
    private ConfiguracionCli() {
        throw new UnsupportedOperationException("No se pueden crear objetos de esta clase");
    }
    
    public static final String MENSAJE_ENTRADA = "Proyecto Biblioteca";
    public static final String MENSAJE_DESPEDIDA = "Saliendo del proyecto. Bye!";
    public static final String CARACTER_SEPARADOR = "-";
    public static final String PREFIJO_ERROR = "HA HABIDO UN ERROR:";
    public static final String OPC_SI = "Aceptar";
    public static final String OPC_NO = "Cancelar";
    public static final String FORMATO_FECHA_ENTRADA = "yyyy-MM-dd";
    public static final String FORMATO_FECHA_VISUAL = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_HORA_VISUAL = "dd/MM/yyyy HH:mm";
    public static final Locale LOCALE = Locale.forLanguageTag("es-CO");
}
