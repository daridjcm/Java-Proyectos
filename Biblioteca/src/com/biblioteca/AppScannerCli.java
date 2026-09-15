package com.biblioteca;

import com.biblioteca.vista.constante.ConfiguracionCli;
import com.cleandev.cli.config.CliConfig;

public class AppScannerCli {
    public AppScannerCli() {
    }

    public static CliConfig configuracionCompleta() {
        return CliConfig.defaultConfig()
                .toBuilder()
                .systemTitle(ConfiguracionCli.MENSAJE_ENTRADA)
                .exitMessage(ConfiguracionCli.MENSAJE_DESPEDIDA)
                .separatorChar(ConfiguracionCli.CARACTER_SEPARADOR)
                .errorPrefix(ConfiguracionCli.PREFIJO_ERROR)
                .defaultNullText(ConfiguracionCli.OPC_SI)
                .defaultNoText(ConfiguracionCli.OPC_NO)
                .inputDateFormat(ConfiguracionCli.FORMATO_FECHA_ENTRADA)
                .visualDateFormat(ConfiguracionCli.FORMATO_FECHA_VISUAL)
                .visualDateTimeFormat(ConfiguracionCli.FORMATO_FECHA_HORA_VISUAL)
                .locale(ConfiguracionCli.LOCALE)
                .build();
    }
}
