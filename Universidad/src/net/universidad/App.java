package net.universidad;
import net.universidad.modelo.Universidad;

import java.time.LocalDate;
import java.util.Scanner;
import net.universidad.conexion.ConexionDB;

public class App {
    public void menu() {
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

        Scanner scanner = new Scanner(System.in);
        Integer opcion;
       do {
            System.out.println("===== MENÚ (elegir una opción) =====");
            System.out.println("1. Crear facultad");
            System.out.println("2. Matricular estudiante");
            System.out.println("3. Listado de facultades");
            System.out.println("4. Información de la Universidad");
            System.out.println("0. Salir del menú");

            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    unimag.crearFacultad();
                    break;
                case 2:
                    unimag.matricularEstudiante();
                    break;                
                case 3:
                    unimag.infoFacultades();
                    break;
                case 4:
                    unimag.infoUniversidad();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("La opción ingresada no existe en el menú.");
                    break;
            }
       } while (opcion != 0);
    }
    
    public static void main(String[] args) {
        try {
            ConexionDB.getConexion().close();
            System.out.println("Base de Datos conectado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        App app = new App();
        app.menu();
    }
}
