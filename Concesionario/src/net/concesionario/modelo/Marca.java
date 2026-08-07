package net.concesionario.modelo;

public class Marca {
    private Integer id;
    private String marca;
    
    public Marca(Integer id, String marca) {
        this.id = id;
        this.marca = marca;
        System.out.println("Marca ID: " + this.id);
        System.out.println("Marca: " + this.marca);
    }
}
