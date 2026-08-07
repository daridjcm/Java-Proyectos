package net.universidad.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Universidad {
    private Integer id;
    private String nombre;
    private Double cantidadEstudiantes;
    private Double cantidadProfesores;
    private Integer cantidadFacultades;
    private ArrayList<String> facultades = new ArrayList<>(Arrays.asList(
        "Ingeniería",
        "Ciencias Básicas",
        "Ciencias de la Educación",
        "Ciencias de la Salud",
        "Humanidades",
        "Ciencias Empresariales y Económicas"
    ));

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
            Double cantidadEstudiantes,
            Double cantidadProfesores,
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
        this.cantidadEstudiantes++;
    }

    public void contratarProfesores() {
        this.cantidadProfesores++;
    }

    public void crearFacultad(String nombreFacultad) {
        facultades.add(nombreFacultad);
        this.cantidadFacultades++;
    }

    public void infoFacultades() {
        System.out.println("\n===== FACULTADES =====");
        for (int i = 1; i <= facultades.size(); i++) {
            System.out.println(i + " - " + facultades.get(i - 1));
        }
        
        this.cantidadFacultades = facultades.size();
    }

    public void info() {
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
