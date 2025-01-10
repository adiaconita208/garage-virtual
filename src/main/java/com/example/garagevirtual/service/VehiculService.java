package com.example.garagevirtual.service;

import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.repository.VehiculRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculService {
    private final VehiculRepository vehiculRepository;



    public VehiculService(VehiculRepository vehiculRepository) {
        this.vehiculRepository = vehiculRepository;
    }

    public List<Vehicul> getAllVehicles() {
        return vehiculRepository.findAll();
    }

    public Vehicul addVehicle(Vehicul vehicul) {
        return vehiculRepository.save(vehicul);
    }

    public void deleteVehicle(Long id) {
        vehiculRepository.deleteById(id);
    }

    public Vehicul saveVehicul(Vehicul vehicul) {
        vehicul.actualizeazaDisponibil(); // Actualizează câmpul disponibil
        return vehiculRepository.save(vehicul);
    }

    public Vehicul updateVehicle(Long id, Vehicul vehiculActualizat) {
        Vehicul vehiculExistent = vehiculRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehiculul nu a fost găsit"));
        vehiculExistent.setItpExpirare(vehiculActualizat.getItpExpirare());
        vehiculExistent.setRcaExpirare(vehiculActualizat.getRcaExpirare());
        vehiculExistent.actualizeazaDisponibil(); // Actualizează câmpul disponibil
        return vehiculRepository.save(vehiculExistent);
    }

    public void deleteVehiculById(Long id) {
        vehiculRepository.deleteById(id);
    }

    public Vehicul getVehiculById(Long id) {
        return vehiculRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicul not found"));
    }
}
