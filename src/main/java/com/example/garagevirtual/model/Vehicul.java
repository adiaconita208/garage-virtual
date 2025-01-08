package com.example.garagevirtual.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vehicul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nrInmatriculare;
    private String serieSasiu;
    private String marca;
    private String model;
    private int anFabricatie;
    private String tip;
    private boolean disponibil;

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String NrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public String getSerieSasiu() {
        return serieSasiu;
    }

    public void setSerieSasiu(String serieSasiu) {
        this.serieSasiu = serieSasiu;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(int anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isDisponibil() {
        return disponibil;
    }

    public void setDisponibil(boolean disponibil) {
        this.disponibil = disponibil;
    }

    public Long getId() {
        return id;
    }
}
