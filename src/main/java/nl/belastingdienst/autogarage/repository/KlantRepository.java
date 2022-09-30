package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Klant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlantRepository extends JpaRepository<Klant, Long> {
}
