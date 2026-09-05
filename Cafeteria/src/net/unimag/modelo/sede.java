
package net.unimag.modelo;

public class sede {
    private Integer idSede;
    private String nombreSede;
    private cafeteria cafeteriaSede;

    public sede() {
    }

    public sede(Integer idSede, String nombreSede, cafeteria cafeteriaSede) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
        this.cafeteriaSede = cafeteriaSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public void setCafeteriaSede(cafeteria cafeteriaSede) {
        this.cafeteriaSede = cafeteriaSede;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public cafeteria getCafeteriaSede() {
        return cafeteriaSede;
    }

    @Override
    public String toString() {
        return "sede" + "\nidSede=" + idSede +
                "\nnombreSede=" + nombreSede + 
                "\ncafeteriaSede=" + cafeteriaSede;
    }
    
    
}
