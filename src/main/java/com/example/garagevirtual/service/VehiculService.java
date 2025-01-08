package com.example.garagevirtual.service;

import com.example.garagevirtual.model.Utilizator;
import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.repository.VehiculRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculService {
    private final VehiculRepository vehiculRepository;

    public List<Vehicul> getVehiclesByUtilizator(Utilizator utilizator) {
        return vehiculRepository.findByProprietar(utilizator);
    }

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

    public Vehicul updateVehicle(Long id, Vehicul updatedVehicul) {
        return vehiculRepository.findById(id)
                .map(vehicul -> {
                    vehicul.setNrInmatriculare(updatedVehicul.getNrInmatriculare());
                    vehicul.setSerieSasiu(updatedVehicul.getSerieSasiu());
                    vehicul.setMarca(updatedVehicul.getMarca());
                    vehicul.setModel(updatedVehicul.getModel());
                    vehicul.setAnFabricatie(updatedVehicul.getAnFabricatie());
                    vehicul.setTip(updatedVehicul.getTip());
                    vehicul.setDisponibil(updatedVehicul.isDisponibil());
                    return vehiculRepository.save(vehicul);
                })
                .orElseThrow(() -> new RuntimeException("Vehicul not found"));
    }

    public Vehicul saveVehicle(Vehicul vehicle) {
        return vehiculRepository.save(vehicle); // Salvează vehiculul în baza de date
    }
}
