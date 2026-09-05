
package net.unimag;

import net.unimag.modelo.cafeteria;
import net.unimag.modelo.sede;

public class app {
    public static void main(String[] args) {
        cafeteria cafe1 = new cafeteria();
        cafe1.setIdCafeteria(88);
        cafe1.setNombreCafeteria("Las Albondigas");
        System.out.println(cafe1);
        
        sede sede1 = new sede();
        sede1.setIdSede(1);
        sede1.setNombreSede("El Chiringuito");
        sede1.setCafeteriaSede(cafe1);
        System.out.println(sede1);
    }
}
