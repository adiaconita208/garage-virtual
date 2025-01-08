package com.example.garagevirtual.service;

import com.example.garagevirtual.model.Vehicul;
import com.example.garagevirtual.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculService {
    private final VehicleRepository vehicleRepository;

    public VehiculService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicul> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicul addVehicle(Vehicul vehicul) {
        return vehicleRepository.save(vehicul);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicul updateVehicle(Long id, Vehicul updatedVehicul) {
        return vehicleRepository.findById(id)
                .map(vehicul -> {
                    vehicul.setNrInmatriculare(updatedVehicul.getNrInmatriculare());
                    vehicul.setSerieSasiu(updatedVehicul.getSerieSasiu());
                    vehicul.setMarca(updatedVehicul.getMarca());
                    vehicul.setModel(updatedVehicul.getModel());
                    vehicul.setAnFabricatie(updatedVehicul.getAnFabricatie());
                    vehicul.setTip(updatedVehicul.getTip());
                    vehicul.setDisponibil(updatedVehicul.isDisponibil());
                    return vehicleRepository.save(vehicul);
                })
                .orElseThrow(() -> new RuntimeException("Vehicul not found"));
    }
}
