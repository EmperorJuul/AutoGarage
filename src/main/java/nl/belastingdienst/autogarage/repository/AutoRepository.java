package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Long> {
}
