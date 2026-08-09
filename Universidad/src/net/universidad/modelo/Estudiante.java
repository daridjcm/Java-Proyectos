package net.universidad.modelo;

import net.universidad.conexion.ConexionDB;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class Estudiante {
    private Integer id;
    private Integer codEstudiantil;
    private Integer cedulaCiudadania;
    private String fechaNacimiento;
    private Integer edad;
    private String nombre;
    private String apellido;

    // Carrera
    private Short idFacultad;
    private String rama;
    private Boolean estado;
    private String fechaMatricula;
    private Object universidad;

    public void pedirDatos(Universidad universidad) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== INGRESO DE DATOS DEL ESTUDIANTE =====");

        System.out.print("Código Estudiantil: ");
        this.codEstudiantil = sc.nextInt();

        System.out.print("Cédula de Ciudadanía: ");
        this.cedulaCiudadania = sc.nextInt();

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        this.fechaNacimiento = sc.next();

        this.edad = Period.between(LocalDate.parse(fechaNacimiento), LocalDate.now()).getYears();

        System.out.print("Nombre: ");
        this.nombre = sc.next();

        System.out.print("Apellido: ");
        this.apellido = sc.next();

        System.out.println("\n--- LISTA DE FACULTADES ---");
        this.universidad = universidad;
        universidad.infoFacultades();
        
        System.out.print("ID Facultad: ");
        this.idFacultad = sc.nextShort();

        System.out.print("Rama: ");
        sc.nextLine();
        this.rama = sc.nextLine();

        System.out.print("Estado (true = activo, false = inactivo): ");
        this.estado = sc.nextBoolean();

        System.out.print("Fecha de matrícula (AAAA-MM-DD): ");
        this.fechaMatricula = sc.next();

        imprimirDatos();
    }

    public void guardarDatos() {
        String sql = """
            INSERT INTO estudiantes (
                cod_estudiantil,
                cedula_ciudadania,
                fecha_nacimiento,
                edad,
                nombre,
                apellido,
                id_facultad,
                rama,
                estado,
                fecha_matricula
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = ConexionDB.getConexion(); 
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, codEstudiantil);
            statement.setInt(2, cedulaCiudadania);
            statement.setString(3, fechaNacimiento);
            statement.setInt(4, edad);
            statement.setString(5, nombre);
            statement.setString(6, apellido);
            statement.setShort(7, idFacultad);
            statement.setString(8, rama);
            statement.setBoolean(9, estado);
            statement.setString(10, fechaMatricula);

            statement.executeUpdate();

            System.out.println("\n✅ Estudiante guardado correctamente en la base de datos.");

        } catch (SQLException e) {
            System.out.println("\n❌ Error al guardar el estudiante.");
            e.printStackTrace();
        }
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
        System.out.println("Rama: " + rama);
        System.out.println("Estado: " + estado);
        System.out.println("Fecha de matrícula: " + fechaMatricula);
        System.out.println("==========================================");
        guardarDatos();
    }
}
