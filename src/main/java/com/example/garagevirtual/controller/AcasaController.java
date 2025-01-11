/** Clasa pentru gestionarea cererilor HTTP pentru pagina principala a aplicatiei.
 * @author Diaconita Adrian
 * @version 12 Ianuarie 2024
 */

package com.example.garagevirtual.controller;

import com.example.garagevirtual.model.TipVehicul;
import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.service.ImageService;
import com.example.garagevirtual.service.VehiculService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AcasaController {

    // Injectare servicii
    private final VehiculService vehiculService;
    private final ImageService imageService;

    // Constructor
    public AcasaController(VehiculService vehiculService, ImageService imageService) {
        this.vehiculService = vehiculService;
        this.imageService = imageService;
    }

    // Metoda pentru afisarea paginii principale
    @GetMapping("/acasa")
    public String showAcasa(Model model) {

        List<Vehicul> vehicule = vehiculService.getAllVehicles();

        vehicule.forEach(vehicul -> {
            // Apelare metoda pentru generare URL imagine
            String imageUrl = imageService.getCarImageUrl(vehicul.getMarca());
            System.out.println("URL imagine pentru " + vehicul.getMarca() + ": " + imageUrl);
            vehicul.setImageUrl(imageUrl); // Setare URL imagine in vehicul
        });

        model.addAttribute("vehicule", vehicule);
        return "acasa";
    }

    // Metoda pentru afisarea paginii de adaugare vehicul
    @GetMapping("/acasa/adauga-vehicul")
    public String showAddVehiclePage(Model model) {
        model.addAttribute("vehicul", new Vehicul());
        return "adauga-vehicul";
    }

    // Metoda pentru adaugarea unui vehicul
    @PostMapping("/acasa/adauga-vehicul")
    public String addVehicle(@RequestParam String nrInmatriculare,
                             @RequestParam String serieSasiu,
                             @RequestParam LocalDate itpExpirare,
                             @RequestParam LocalDate rcaExpirare,
                             @RequestParam String marca,
                             @RequestParam String model,
                             @RequestParam int anFabricatie,
                             @RequestParam String tip,
                             Model uimodel) {

        // Preluare an curent
        int currentAnFabricatie = LocalDate.now().getYear();

        // Validare an fabricatie
        if (anFabricatie > currentAnFabricatie) {
            uimodel.addAttribute("error", "Anul fabricației nu poate fi mai mare decât " + currentAnFabricatie + ".");
            return "adauga-vehicul";
        }

        // Validare serie sasiu
        if (serieSasiu.length() != 17) {
            uimodel.addAttribute("error", "Seria de șasiu introdusă nu este validă");
            return "adauga-vehicul"; // Redirecționează utilizatorul înapoi la formular
        }

        Vehicul vehicul = new Vehicul();
        vehicul.setNrInmatriculare(nrInmatriculare);
        vehicul.setSerieSasiu(serieSasiu);
        vehicul.setItpExpirare(itpExpirare);
        vehicul.setRcaExpirare(rcaExpirare);
        vehicul.setMarca(marca);
        vehicul.setModel(model);
        vehicul.setAnFabricatie(anFabricatie);

        // Validare TipVehicul
        try {
            vehicul.setTip(TipVehicul.valueOf(tip.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipul selectat nu este valid.");
        }

        // Salvează vehiculul
        vehiculService.saveVehicul(vehicul);

        // Redirectionează catre pagina "Acasa"
        return "redirect:/acasa";
    }

    // Metoda pentru stergerea unui vehicul
    @GetMapping("/delete-vehicul/{id}")
    public String deleteVehicul(@PathVariable Long id) {
        vehiculService.deleteVehiculById(id);
        return "redirect:/acasa"; // Redirecționează către pagina principală
    }

    // Metoda pentru afisarea paginii de editare a unui vehicul
    @GetMapping("/edit-vehicul/{id}")
    public String editVehiculForm(@PathVariable Long id, Model model) {
        Vehicul vehicul = vehiculService.getVehiculById(id);
        if (vehicul == null) {
            throw new IllegalArgumentException("Vehiculul cu ID-ul " + id + " nu a fost găsit.");
        }
        model.addAttribute("vehicul", vehicul);
        return "edit-vehicul";
    }

    // Metoda pentru editarea unui vehicul
    @PostMapping("/edit-vehicul")
    public String editVehicul(@RequestParam Long id,
                              @RequestParam String nrInmatriculare,
                              @RequestParam String serieSasiu,
                              @RequestParam LocalDate itpExpirare,
                              @RequestParam LocalDate rcaExpirare,
                              @RequestParam String marca,
                              @RequestParam String model,
                              @RequestParam int anFabricatie,
                              @RequestParam String tip,
                              Model uimodel) {

        // Validare serie sasiu
        if (serieSasiu.length() != 17) {
            Vehicul vehicul = vehiculService.getVehiculById(id); // Obține vehiculul din baza de date
            vehicul.setNrInmatriculare(nrInmatriculare);
            vehicul.setSerieSasiu(serieSasiu);
            vehicul.setItpExpirare(itpExpirare);
            vehicul.setRcaExpirare(rcaExpirare);
            vehicul.setMarca(marca);
            vehicul.setModel(model);
            vehicul.setAnFabricatie(anFabricatie);
            vehicul.setTip(TipVehicul.valueOf(tip.toUpperCase()));

            uimodel.addAttribute("vehicul", vehicul); // Adaugă vehiculul în model
            uimodel.addAttribute("error", "Seria de șasiu trebuie să aibă exact 17 caractere."); // Mesaj de eroare
            return "edit-vehicul"; // Redirecționează utilizatorul la formularul de editare
        }

        int currentAnFabricatie = LocalDate.now().getYear();

        // Validare an fabricatie
        if (anFabricatie > currentAnFabricatie) {
            Vehicul vehicul = vehiculService.getVehiculById(id);
            vehicul.setNrInmatriculare(nrInmatriculare);
            vehicul.setSerieSasiu(serieSasiu);
            vehicul.setItpExpirare(itpExpirare);
            vehicul.setRcaExpirare(rcaExpirare);
            vehicul.setMarca(marca);
            vehicul.setModel(model);
            vehicul.setAnFabricatie(anFabricatie);
            vehicul.setTip(TipVehicul.valueOf(tip.toUpperCase()));

            uimodel.addAttribute("vehicul", vehicul);
            uimodel.addAttribute("error", "Anul fabricației nu poate fi mai mare decât " + currentAnFabricatie + ".");
            return "edit-vehicul";
        }

        Vehicul vehicul = vehiculService.getVehiculById(id);
        vehicul.setNrInmatriculare(nrInmatriculare);
        vehicul.setSerieSasiu(serieSasiu);
        vehicul.setItpExpirare(itpExpirare);
        vehicul.setRcaExpirare(rcaExpirare);
        vehicul.setMarca(marca);
        vehicul.setModel(model);
        vehicul.setAnFabricatie(anFabricatie);

        // Conversie tip
        vehicul.setTip(TipVehicul.valueOf(tip.toUpperCase()));

        vehiculService.saveVehicul(vehicul); // Salvează vehiculul actualizat

        return "redirect:/acasa";
    }
}