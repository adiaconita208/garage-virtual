package com.example.garagevirtual.controller;

import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.service.VehiculService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
                             @RequestParam(required = false, defaultValue = "false") boolean disponibil) {

        Vehicul vehicul = new Vehicul();
        vehicul.setNrInmatriculare(nrInmatriculare);
        vehicul.setSerieSasiu(serieSasiu);
        vehicul.setMarca(marca);
        vehicul.setModel(model);
        vehicul.setAnFabricatie(anFabricatie);
        vehicul.setTip(tip);
        vehicul.setDisponibil(disponibil);

        // Salvează vehiculul
        vehiculService.saveVehicul(vehicul);

        // Redirecționează către pagina "Acasă"
        return "redirect:/acasa";
    }
}