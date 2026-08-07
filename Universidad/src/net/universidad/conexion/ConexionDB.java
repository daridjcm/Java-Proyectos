package net.universidad.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    public static Connection getConexion() {

        String url = "jdbc:mariadb://"
                + Config.get("db.host") + ":"
                + Config.get("db.port") + "/"
                + Config.get("db.name");

        try {
            return DriverManager.getConnection(
                    url,
                    Config.get("db.user"), Config.get("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar", e);
        }
    }
}
