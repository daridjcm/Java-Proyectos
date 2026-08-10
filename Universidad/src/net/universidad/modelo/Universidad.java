package net.universidad.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.universidad.conexion.ConexionDB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Universidad {
    private Integer id;
    private String nombre;
    private Short cantidadEstudiantes;
    private Short cantidadProfesores;
    private Integer cantidadFacultades = 0; // Integer porque la funcion size() solo retorna dato de tipo Int.
    private ArrayList<String> facultades = new ArrayList<>(Arrays.asList(
        "Ingeniería",
        "Ciencias Básicas",
        "Ciencias de la Educación",
        "Ciencias de la Salud",
        "Humanidades",
        "Ciencias Empresariales y Económicas"
    )); // Remplazar esto accediendo a los datos de la tabla facultades de la DB 

    private Boolean acreditada;
    private Boolean esPublica;
    private LocalDate fechaFundacion;
    private String direccion;
    private String ciudad;
    private String pais;
    private String paginaWeb;

    public Universidad(
            Integer id,
            String nombre,
            Short cantidadEstudiantes,
            Short cantidadProfesores,
            Boolean acreditada,
            Boolean esPublica,
            LocalDate fechaFundacion,
            String direccion,
            String ciudad,
            String pais,
            String paginaWeb) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadEstudiantes = cantidadEstudiantes;
        this.cantidadProfesores = cantidadProfesores;
        this.acreditada = acreditada;
        this.esPublica = esPublica;
        this.fechaFundacion = fechaFundacion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.paginaWeb = paginaWeb;
    }

    public void matricularEstudiante() {
        Estudiante me = new Estudiante();
        me.pedirDatos(this);
        this.cantidadEstudiantes++;
    }

    public void contratarProfesores() {
        this.cantidadProfesores++;
    }

    public void crearFacultad() {
        Scanner sc = new Scanner(System.in);
        String inputFacu;
        
        System.out.print("Nombre de la facultad: ");
        inputFacu = sc.next();
        facultades.add(inputFacu);
        
        this.cantidadFacultades++;
        guardarDatos(inputFacu);
    }
    
    
    public void guardarDatos(String nombreFacultad) {
        String sql = """
            INSERT INTO facultades (facultad) VALUES (?)""";
        try (Connection connection = ConexionDB.getConexion(); 
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombreFacultad);
            statement.executeUpdate();

            System.out.println("Facultad guardada correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar la facultad.");
            e.printStackTrace();
        }
    }


    public void infoFacultades() {
        System.out.println("===== FACULTADES =====");
        for (int i = 1; i <= facultades.size(); i++) {
            System.out.println(i + " - " + facultades.get(i - 1));
        }
        
        this.cantidadFacultades = facultades.size();
    }

    public void infoUniversidad() {
        System.out.println("ID Universidad: " + this.id);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Cantidad de estudiantes: " + this.cantidadEstudiantes);
        System.out.println("Cantidad de profesores: " + this.cantidadProfesores);
        this.infoFacultades();
        System.out.println("Cantidad de facultades: " + this.cantidadFacultades);

        if (this.acreditada) {
            System.out.println("\nLa universidad está acreditada.");
        } else {
            System.out.println("\nLa universidad no está acreditada.");
        }

        if (this.esPublica) {
            System.out.println("La universidad es pública.");
        } else {
            System.out.println("La universidad es privada.");
        }

        System.out.println("Fecha de fundación: " + this.fechaFundacion);

        System.out.println(
            "\nDirección de " + this.nombre + ": " +
            this.direccion + ", " +
            this.ciudad + ", " +
            this.pais
        );

        System.out.println("Página web: " + this.paginaWeb);
    }
}
