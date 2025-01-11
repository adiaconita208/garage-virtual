/** Clasa contine logica pentru operatiile CRUD pe tabela Vehicul din baza de date.
 * @author Diaconita Adrian
 * @version 12 Ianuarie 2024
 */

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

    // Metoda pentru obtinerea tuturor vehiculelor
    public List<Vehicul> getAllVehicles() {
        return vehiculRepository.findAll();
    }

    // Metoda pentru adaugarea unui vehicul
    public Vehicul addVehicle(Vehicul vehicul) {
        return vehiculRepository.save(vehicul);
    }

    // Metoda pentru actualizarea unui vehicul
    public void deleteVehicle(Long id) {
        vehiculRepository.deleteById(id);
    }

    // Metoda pentru salvarea unui vehicul in baza de date
    public Vehicul saveVehicul(Vehicul vehicul) {
        vehicul.actualizeazaDisponibil(); // Actualizează câmpul disponibil
        return vehiculRepository.save(vehicul);
    }

    // Metoda pentru actualizarea unui vehicul
    public Vehicul updateVehicle(Long id, Vehicul vehiculActualizat) {
        Vehicul vehiculExistent = vehiculRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehiculul nu a fost găsit"));
        vehiculExistent.setItpExpirare(vehiculActualizat.getItpExpirare());
        vehiculExistent.setRcaExpirare(vehiculActualizat.getRcaExpirare());
        vehiculExistent.actualizeazaDisponibil(); // Actualizează câmpul disponibil
        return vehiculRepository.save(vehiculExistent);
    }

    // Metoda pentru stergerea unui vehicul dupa id
    public void deleteVehiculById(Long id) {
        vehiculRepository.deleteById(id);
    }

    // Metoda pentru obtinerea unui vehicul dupa id
    public Vehicul getVehiculById(Long id) {
        return vehiculRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicul not found"));
    }
}
