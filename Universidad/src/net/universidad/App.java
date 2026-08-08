package net.universidad;

import java.time.LocalDate;
import net.universidad.modelo.Estudiante;
import net.universidad.modelo.Universidad;

import net.universidad.conexion.ConexionDB;

public class App {
    public static void objetos() {
        Estudiante me = new Estudiante();
        
        Universidad unimag = new Universidad(
            1,
            "Universidad del Magdalena",
            (short)28227,
            (short)1204,
            true,
            true,
            LocalDate.of(1958, 10, 27),
            "Carrera 32 No 22 – 08",
            "Santa Marta",
            "Colombia",
            "www.unimagdalena.edu.co"
        );
        
        unimag.info();
        me.pedirDatos(unimag);
    }
    
    public static void main(String[] args) {
        try {
            ConexionDB.getConexion().close();
            System.out.println("Conectado correctamente.");
            objetos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
