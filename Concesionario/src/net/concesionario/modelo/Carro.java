package net.concesionario.modelo;

import java.time.OffsetDateTime;

public class Carro {
    private Marca marca;
    private Integer id;
    private String color;
    private OffsetDateTime fechaIngreso;

    public Carro(Marca marca, Integer id, String color, OffsetDateTime fechaIngreso) {
        this.marca = marca;
        this.id = id;
        this.color = color;
        this.fechaIngreso = fechaIngreso;
        System.out.println("-----------------------");
        System.out.println("Carro ID: " + this.id);
        System.out.println("Color: " + this.color);
        System.out.println("Fecha de ingreso: " + this.fechaIngreso);
    }
}
