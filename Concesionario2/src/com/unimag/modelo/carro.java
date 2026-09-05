
package com.unimag.modelo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class carro {
    private Integer idCarro;
    private String placaCarra;
    private marca marcacarro;
    private BigDecimal precioCarro;
    private OffsetDateTime fechaCarro;
    private colorcito colorCarro;

    public carro() {
    }

    public carro(Integer idCarro, String placaCarra, marca marcacarro, BigDecimal precioCarro, OffsetDateTime fechaCarro) {
        this.idCarro = idCarro;
        this.placaCarra = placaCarra;
        this.marcacarro = marcacarro;
        this.precioCarro = precioCarro;
        this.fechaCarro = fechaCarro;
        this.colorCarro = colorCarro;
    }

    public carro(colorcito colorCarro) {
        this.colorCarro = colorCarro;
    }

    public Integer getIdCarro() {
        return idCarro;
    }

    public String getPlacaCarra() {
        return placaCarra;
    }

    public marca getMarcacarro() {
        return marcacarro;
    }

    public BigDecimal getPrecioCarro() {
        return precioCarro;
    }

    public OffsetDateTime getFechaCarro() {
        return fechaCarro;
    }

    public void setIdCarro(Integer idCarro) {
        this.idCarro = idCarro;
    }

    public void setPlacaCarra(String placaCarra) {
        this.placaCarra = placaCarra;
    }

    public void setMarcacarro(marca marcacarro) {
        this.marcacarro = marcacarro;
    }

    public void setPrecioCarro(BigDecimal precioCarro) {
        this.precioCarro = precioCarro;
    }

    public void setFechaCarro(OffsetDateTime fechaCarro) {
        this.fechaCarro = fechaCarro;
    }

    public colorcito getColorCarro() {
        return colorCarro;
    }

    public void setColorCarro(colorcito colorCarro) {
        this.colorCarro = colorCarro;
    }

    @Override
    public String toString() {
        return "carro" + "\nidCarro=" + idCarro + ""
                + "\nplacaCarra=" + placaCarra +
                "\nmarcacarro=" + marcacarro +
                "\nprecioCarro=" + precioCarro +
                "\nfechaCarro=" + fechaCarro +
                "\ncolorCarro=" + colorCarro;
    }

    
    
    
    
}
