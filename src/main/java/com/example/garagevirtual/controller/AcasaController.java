package com.example.garagevirtual.controller;

import com.example.garagevirtual.model.Utilizator;
import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.service.SessionService;
import com.example.garagevirtual.service.UtilizatorService;
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
    private final UtilizatorService utilizatorService;
    private final SessionService sessionService;

    public AcasaController(VehiculService vehiculService, UtilizatorService utilizatorService, SessionService sessionService) {
        this.vehiculService = vehiculService;
        this.utilizatorService = utilizatorService;
        this.sessionService = sessionService;
    }

    @GetMapping("/acasa")
    public String showAcasa(Model model) {
        Utilizator currentUser = utilizatorService.findUtilizatorByEmail("john.doe@example.com").orElseThrow();
        List<Vehicul> userVehicles = vehiculService.getVehiclesByUtilizator(currentUser);
        model.addAttribute("vehicule", userVehicles);
        model.addAttribute("utilizator", currentUser);
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
                             @RequestParam boolean disponibil) {
        // Obține utilizatorul curent din sesiune
        Utilizator currentUser = sessionService.getCurrentUser();

        // Creează vehiculul și asociază-l cu utilizatorul curent
        Vehicul vehicul = new Vehicul();
        vehicul.setNrInmatriculare(nrInmatriculare);
        vehicul.setSerieSasiu(serieSasiu);
        vehicul.setMarca(marca);
        vehicul.setModel(model);
        vehicul.setAnFabricatie(anFabricatie);
        vehicul.setTip(tip);
        vehicul.setDisponibil(disponibil);
        vehicul.setProprietar(currentUser);

        // Salvează vehiculul
        vehiculService.saveVehicle(vehicul);

        // Redirecționează către pagina "Acasă"
        return "redirect:/acasa";
    }
}