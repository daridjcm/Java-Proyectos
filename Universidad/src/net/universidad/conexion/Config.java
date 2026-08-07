package net.universidad.conexion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getResourceAsStream(
        "/net/universidad/conexion/database.properties")) {

            if (input == null) {
                throw new RuntimeException("No se encontró database.properties");
            }

            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}