
package net.unimag.modelo;

public class cafeteria {
    private Integer idCafeteria;
    private String nombreCafeteria;

    public cafeteria() {
    }

    public Integer getIdCafeteria() {
        return idCafeteria;
    }

    public String getNombreCafeteria() {
        return nombreCafeteria;
    }

    public void setIdCafeteria(Integer idCafeteria) {
        this.idCafeteria = idCafeteria;
    }

    public void setNombreCafeteria(String nombreCafeteria) {
        this.nombreCafeteria = nombreCafeteria;
    }

    @Override
    public String toString() {
        return "cafeteria\n" + "idCafeteria=" + idCafeteria + "\nnombreCafeteria=" + nombreCafeteria;
    }

    

    

    
    
}
