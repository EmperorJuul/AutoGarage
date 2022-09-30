package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Reparatie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReparatieRepository extends JpaRepository<Reparatie, Long> {
}
