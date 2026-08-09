package net.universidad;
import net.universidad.modelo.Universidad;

import java.time.LocalDate;
import net.universidad.conexion.ConexionDB;

public class App {
    public static void main(String[] args) {
        Universidad unimag = new Universidad(
            1,
            "Universidad del Magdalena",
            (short) 28227,
            (short) 1204,
            true,
            true,
            LocalDate.of(1958, 10, 27),
            "Carrera 32 No 22 – 08",
            "Santa Marta",
            "Colombia",
            "www.unimagdalena.edu.co"
        );
        unimag.matricularEstudiante();
        
        try {
            ConexionDB.getConexion().close();
            System.out.println("Conectado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
