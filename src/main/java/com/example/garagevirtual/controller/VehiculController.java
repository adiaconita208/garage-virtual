/** Clasa pentru gestionarea cererilor HTTP pentru operatiuni asupra vehiculelor.
 * @author Diaconita Adrian
 * @version 12 Ianuarie 2024
 */

package com.example.garagevirtual.controller;

import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.service.ImageService;
import com.example.garagevirtual.service.VehiculService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicule")
public class VehiculController {
    private final VehiculService vehiculService;

    public VehiculController(VehiculService vehiculService) {
        this.vehiculService = vehiculService;
    }

    @Autowired
    private ImageService imageService;

    // Metoda pentru afisarea paginii principale
    @GetMapping("/acasa")
    public String showAcasa(Model model) {
        // Obținere lista vehicule
        List<Vehicul> vehicule = vehiculService.getAllVehicles();
        // Iterare prin vehicule și generare URL imagine
        vehicule.forEach(vehicul -> {
            String imageUrl = imageService.getCarImageUrl(vehicul.getMarca());
            vehicul.setImageUrl(imageUrl);
        });
        model.addAttribute("vehicule", vehicule);
        return "acasa";
    }

    // Metoda pentru obtinerea tuturor vehiculelor
    @GetMapping
    public List<Vehicul> getAllVehicles() {
        return vehiculService.getAllVehicles();
    }

    // Metoda pentru adaugarea unui vehicul
    @PostMapping
    public Vehicul addVehicle(@RequestBody Vehicul vehicul) {
        return vehiculService.addVehicle(vehicul);
    }

    // Metoda pentru actualizarea unui vehicul
    @PutMapping("/{id}")
    public Vehicul updateVehicle(@PathVariable Long id, @RequestBody Vehicul vehicul) {
        return vehiculService.updateVehicle(id, vehicul);
    }

    // Metoda pentru stergerea unui vehicul
    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehiculService.deleteVehicle(id);
    }
}
