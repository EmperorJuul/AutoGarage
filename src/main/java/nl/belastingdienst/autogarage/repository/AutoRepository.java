package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Car, Long> {
}
