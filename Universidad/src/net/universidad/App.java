package net.universidad;

import java.time.LocalDate;
import net.universidad.modelo.Estudiante;
import net.universidad.modelo.Universidad;

public class App {
    public static void main(String[] args) {
        Estudiante me = new Estudiante();
        
        Universidad unimag = new Universidad(
            1,
            "Universidad del Magdalena",
            28.227,
            1.204,
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
}
