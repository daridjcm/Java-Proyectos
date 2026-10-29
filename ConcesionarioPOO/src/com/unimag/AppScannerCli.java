package com.unimag;

import com.cleandev.cli.config.CliConfig;
import com.unimag.vista.constante.ConfigCLI;

public final class AppScannerCli {

    public AppScannerCli() {
    }
    
    public static CliConfig configuracionCompleta(){
        return CliConfig.defaultConfig()
                .toBuilder()
                .systemTitle(ConfigCLI.TITULO_SISTEMA)
                .exitMessage(ConfigCLI.MENSAJE_SALIDA)
                .separatorChar(ConfigCLI.CARACTER_SEPARADOR)
                .separatorLength(ConfigCLI.LONGITUD_SEPARADOR)
                .errorPrefix(ConfigCLI.PREFIJO_ERROR)
                .defaultNullText(ConfigCLI.TEXTO_NULO)
                .defaultNoText(ConfigCLI.TEXTO_NO)
                .inputDateFormat(ConfigCLI.FORMATO_FECHA_ENTRADA)
                .visualDateFormat(ConfigCLI.FORMATO_FECHA_VISUAL)
                .visualDateTimeFormat(ConfigCLI.FORMATO_FECHA_HORA_VISUAL)
                .locale(ConfigCLI.LOCALE)
                .build();
    }
}