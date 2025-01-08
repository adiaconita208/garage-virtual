package com.example.garagevirtual.controller;

import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.service.VehiculService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicule")
public class VehiculController {
    private final VehiculService vehiculService;

    public VehiculController(VehiculService vehiculService) {
        this.vehiculService = vehiculService;
    }

    @GetMapping
    public List<Vehicul> getAllVehicles() {
        return vehiculService.getAllVehicles();
    }

    @PostMapping
    public Vehicul addVehicle(@RequestBody Vehicul vehicul) {
        return vehiculService.addVehicle(vehicul);
    }

    @PutMapping("/{id}")
    public Vehicul updateVehicle(@PathVariable Long id, @RequestBody Vehicul vehicul) {
        return vehiculService.updateVehicle(id, vehicul);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehiculService.deleteVehicle(id);
    }
}
