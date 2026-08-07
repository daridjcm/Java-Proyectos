package net.concesionario;

import net.concesionario.modelo.Carro;
import net.concesionario.modelo.Marca;
import java.time.OffsetDateTime;

public class App {
    public static void main(String[] args) {
        Marca obj1 = new Marca(1, "KIA");
        Carro obj2 = new Carro(obj1, 1, "morado", OffsetDateTime.now());
    }
}
