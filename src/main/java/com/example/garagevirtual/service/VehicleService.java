package com.example.garagevirtual.service;

import com.example.garagevirtual.model.Vehicle;
import com.example.garagevirtual.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setLicensePlate(updatedVehicle.getLicensePlate());
                    vehicle.setVIN(updatedVehicle.getVIN());
                    vehicle.setMake(updatedVehicle.getMake());
                    vehicle.setModel(updatedVehicle.getModel());
                    vehicle.setYear(updatedVehicle.getYear());
                    vehicle.setType(updatedVehicle.getType());
                    vehicle.setAvailable(updatedVehicle.isAvailable());
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }
}
