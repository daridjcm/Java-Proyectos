package net.universidad.modelo;

import java.time.LocalDate;
import java.util.Scanner;

public class Estudiante {

    private Integer id;
    private Integer codEstudiantil;
    private Integer cedulaCiudadania;
    private String fechaNacimiento;
    private Short edad;
    private String nombre;
    private String apellido;

    // Carrera
    private Short idFacultad;
    private String rama;
    private Boolean estado;
    private String fechaMatricula;

    public void pedirDatos() {

        Universidad unimag = new Universidad(
            1,
            "Universidad del Magdalena",
            28227,
            1204,
            6,
            true,
            true,
            LocalDate.of(1958, 10, 27),
            "Carrera 32 No 22 – 08",
            "Santa Marta",
            "Colombia",
            "www.unimagdalena.edu.co");

        Scanner sc = new Scanner(System.in);

        System.out.println("===== INGRESO DE DATOS DEL ESTUDIANTE =====");

        System.out.print("ID: ");
        this.id = sc.nextInt();

        System.out.print("Código Estudiantil: ");
        this.codEstudiantil = sc.nextInt();

        System.out.print("Cédula de Ciudadanía: ");
        this.cedulaCiudadania = sc.nextInt();

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        this.fechaNacimiento = sc.next();

        System.out.print("Edad: ");
        this.edad = sc.nextShort();

        System.out.print("Nombre: ");
        this.nombre = sc.next();

        System.out.print("Apellido: ");
        this.apellido = sc.next();

        System.out.println("\n--- LISTA DE FACULTADES ---");
        unimag.infoFacultades();

        System.out.print("ID Facultad: ");
        this.idFacultad = sc.nextShort();

        System.out.print("Rama: ");
        sc.nextLine(); // limpiar buffer
        this.rama = sc.nextLine();

        System.out.print("Estado (true = activo, false = inactivo): ");
        this.estado = sc.nextBoolean();

        System.out.print("Fecha de matrícula (AAAA-MM-DD): ");
        this.fechaMatricula = sc.next();

        imprimirDatos();
    }

    public void imprimirDatos() {
        System.out.println("\n========== DATOS DEL ESTUDIANTE ==========");
        System.out.println("ID: " + id);
        System.out.println("Código Estudiantil: " + codEstudiantil);
        System.out.println("Cédula: " + cedulaCiudadania);
        System.out.println("Fecha de nacimiento: " + fechaNacimiento);
        System.out.println("Edad: " + edad);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("ID Facultad: " + idFacultad);
        System.out.println("Rama/Carrera: " + rama);
        System.out.println("Estado: " + estado);
        System.out.println("Fecha de matrícula: " + fechaMatricula);
        System.out.println("==========================================");
    }
}