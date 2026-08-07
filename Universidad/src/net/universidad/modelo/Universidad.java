package net.universidad.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Universidad {
    private Integer id;
    private String nombre;
    private Integer cantidadEstudiantes;
    private Integer cantidadProfesores;

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
            Integer cantidadEstudiantes,
            Integer cantidadProfesores,
            Integer cantidadFacultades,
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
    }

    public void infoFacultades() {

        System.out.println("\n===== FACULTADES =====");
        System.out.println("Total de facultades: " + facultades.size());

        for (int i = 1; i <= facultades.size(); i++) {
            System.out.println(i + " - " + facultades.get(i - 1));
        }
    }

    public void infoU() {
        
        System.out.println("\nDirección de " + this.nombre + ": " + this.direccion + ", " + this.ciudad + ", " + this.pais);
        if (this.esPublica) {
            System.out.println("La universidad es pública.");
        } else {
            System.out.println("La universidad es privada.");
        }
    }
}