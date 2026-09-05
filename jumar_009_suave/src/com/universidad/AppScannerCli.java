
package com.universidad;

import com.cleandev.cli.config.CliConfig;
import com.universidad.vista.constante.configuracionCli;

public final class AppScannerCli {

    public AppScannerCli() {
    }
    
    public static CliConfig configuracionCompleta(){
        return CliConfig.defaultConfig()
                .toBuilder()
                .systemTitle(configuracionCli.TITULO_SISTEMA)
                .exitMessage(configuracionCli.MENSAJE_SALIDA)
                .separatorChar(configuracionCli.CARACTER_SEPARADOR)
                .separatorLength(configuracionCli.LONGITUD_SEPARADOR)
                .errorPrefix(configuracionCli.PREFIJO_ERROR)
                .defaultNullText(configuracionCli.TEXTO_NULO)
                .defaultNoText(configuracionCli.TEXTO_NO)
                .inputDateFormat(configuracionCli.FORMATO_FECHA_ENTRADA)
                .visualDateFormat(configuracionCli.FORMATO_FECHA_VISUAL)
                .visualDateTimeFormat(configuracionCli.FORMATO_FECHA_HORA_VISUAL)
                .locale(configuracionCli.LOCALE)
                .build();
    }
}
