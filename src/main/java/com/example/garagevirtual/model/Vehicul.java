/** Clasa reprezintă entitatea principală a aplicației modeland informatiile despre vehicul.
 * @author Diaconita Adrian
 * @version 12 Ianuarie 2024
 */

package com.example.garagevirtual.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Data
@Entity
public class Vehicul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nr_inmatriculare")
    private String nrInmatriculare;

    private String serieSasiu;
    private String marca;
    private String model;
    private int anFabricatie;

    @Enumerated(EnumType.STRING)
    private TipVehicul tip;

    private boolean disponibil;

    private LocalDate itpExpirare;
    private LocalDate rcaExpirare;

    private String imageUrl;

    // Gettere și settere
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getItpExpirare() {
        return itpExpirare;
    }

    public void setItpExpirare(LocalDate itpExpirare) {
        this.itpExpirare = itpExpirare;
    }

    public LocalDate getRcaExpirare() {
        return rcaExpirare;
    }

    public void setRcaExpirare(LocalDate rcaExpirare) {
        this.rcaExpirare = rcaExpirare;
    }



    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
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

    public TipVehicul getTip() {
        return tip;
    }

    public void setTip(TipVehicul tip) {
        this.tip = tip;
    }

    public boolean isDisponibil() {
        // Logica: true dacă ambele date sunt valide (după ziua curentă)
        LocalDate today = LocalDate.now();
        return (itpExpirare != null && itpExpirare.isAfter(today)) &&
                (rcaExpirare != null && rcaExpirare.isAfter(today));
    }

    public void actualizeazaDisponibil() {
        this.disponibil = isDisponibil();
    }

    public Long getId() {
        return id;
    }
}
