
package com.unimag.modelo;

public class marca {
    private Integer idMarca;
    private String nombreMarca;

    public marca() {
    }

    public marca(Integer idMarca, String nombreMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    @Override
    public String toString() {
        return "marca\n" +
                "idMarca=" + idMarca + 
                "\nnombreMarca=" + nombreMarca;
    }
    
    
}
