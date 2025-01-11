/** Clasa gestionează operațiunile de acces la baza de date pentru entitatea Vehicul.
 * @author Diaconita Adrian
 * @version 12 Ianuarie 2024
 */

package com.example.garagevirtual.repository;

import com.example.garagevirtual.model.Vehicul;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculRepository extends JpaRepository<Vehicul, Long> {

}
