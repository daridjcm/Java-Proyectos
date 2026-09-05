
package com.unimag.modelo;

public class colorcito {
   private Integer idColorcito;
   private String nombreColorcito;

    public colorcito(Integer idColorcito, String nombreColorcito) {
        this.idColorcito = idColorcito;
        this.nombreColorcito = nombreColorcito;
    }

    public Integer getIdColorcito() {
        return idColorcito;
    }

    public String getNombreColorcito() {
        return nombreColorcito;
    }

    public void setIdColorcito(Integer idColorcito) {
        this.idColorcito = idColorcito;
    }

    public void setNombreColorcito(String nombreColorcito) {
        this.nombreColorcito = nombreColorcito;
    }

    @Override
    public String toString() {
        return "colorcito" + 
            "\nidColorcito=" + idColorcito
            + "\nnombreColorcito=" + nombreColorcito;
    }
}
