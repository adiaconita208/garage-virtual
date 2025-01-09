package com.example.garagevirtual.controller;

import com.example.garagevirtual.model.TipVehicul;
import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.service.VehiculService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AcasaController {

    private final VehiculService vehiculService;

    public AcasaController(VehiculService vehiculService) {
        this.vehiculService = vehiculService;
    }

    @GetMapping("/acasa")
    public String showAcasa(Model model) {

        List<Vehicul> vehicule = vehiculService.getAllVehicles();
        model.addAttribute("vehicule", vehicule);
        return "acasa";
    }

    @GetMapping("/acasa/adauga-vehicul")
    public String showAddVehiclePage(Model model) {
        model.addAttribute("vehicul", new Vehicul());
        return "adauga-vehicul";
    }

    @PostMapping("/acasa/adauga-vehicul")
    public String addVehicle(@RequestParam String nrInmatriculare,
                             @RequestParam String serieSasiu,
                             @RequestParam String marca,
                             @RequestParam String model,
                             @RequestParam int anFabricatie,
                             @RequestParam String tip,
                             @RequestParam(required = false, defaultValue = "false") boolean disponibil,
                             Model uimodel) {

        if (serieSasiu.length() != 17) {
            uimodel.addAttribute("error", "Seria de șasiu introdusă nu este validă");
            return "adauga-vehicul"; // Redirecționează utilizatorul înapoi la formular
        }

        Vehicul vehicul = new Vehicul();
        vehicul.setNrInmatriculare(nrInmatriculare);
        vehicul.setSerieSasiu(serieSasiu);
        vehicul.setMarca(marca);
        vehicul.setModel(model);
        vehicul.setAnFabricatie(anFabricatie);
        vehicul.setDisponibil(disponibil);

        try {
            vehicul.setTip(TipVehicul.valueOf(tip.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipul selectat nu este valid.");
        }

        // Salvează vehiculul
        vehiculService.saveVehicul(vehicul);

        // Redirecționează către pagina "Acasă"
        return "redirect:/acasa";
    }

    @GetMapping("/delete-vehicul/{id}")
    public String deleteVehicul(@PathVariable Long id) {
        vehiculService.deleteVehiculById(id);
        return "redirect:/acasa"; // Redirecționează către pagina principală
    }

    @GetMapping("/edit-vehicul/{id}")
    public String editVehiculForm(@PathVariable Long id, Model model) {
        Vehicul vehicul = vehiculService.getVehiculById(id);
        if (vehicul == null) {
            throw new IllegalArgumentException("Vehiculul cu ID-ul " + id + " nu a fost găsit.");
        }
        model.addAttribute("vehicul", vehicul);
        return "edit-vehicul";
    }

    @PostMapping("/edit-vehicul")
    public String editVehicul(@RequestParam Long id,
                              @RequestParam String nrInmatriculare,
                              @RequestParam String serieSasiu,
                              @RequestParam String marca,
                              @RequestParam String model,
                              @RequestParam int anFabricatie,
                              @RequestParam String tip,
                              @RequestParam boolean disponibil,
                              Model uimodel) {
        if (serieSasiu.length() != 17) {
            Vehicul vehicul = vehiculService.getVehiculById(id); // Obține vehiculul din baza de date
            vehicul.setNrInmatriculare(nrInmatriculare);
            vehicul.setSerieSasiu(serieSasiu); // Setează valorile introduse în formular
            vehicul.setMarca(marca);
            vehicul.setModel(model);
            vehicul.setAnFabricatie(anFabricatie);
            vehicul.setTip(TipVehicul.valueOf(tip.toUpperCase()));
            vehicul.setDisponibil(disponibil);

            uimodel.addAttribute("vehicul", vehicul); // Adaugă vehiculul în model
            uimodel.addAttribute("error", "Seria de șasiu trebuie să aibă exact 17 caractere."); // Mesaj de eroare
            return "edit-vehicul"; // Redirecționează utilizatorul la formularul de editare
        }

        Vehicul vehicul = vehiculService.getVehiculById(id);
        vehicul.setNrInmatriculare(nrInmatriculare);
        vehicul.setSerieSasiu(serieSasiu);
        vehicul.setMarca(marca);
        vehicul.setModel(model);
        vehicul.setAnFabricatie(anFabricatie);

        // Conversie tip
        vehicul.setTip(TipVehicul.valueOf(tip.toUpperCase()));
        vehicul.setDisponibil(disponibil);

        vehiculService.saveVehicul(vehicul); // Salvează vehiculul actualizat

        return "redirect:/acasa";
    }
}