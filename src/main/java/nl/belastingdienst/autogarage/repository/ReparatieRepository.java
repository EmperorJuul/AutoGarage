package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReparatieRepository extends JpaRepository<Repair, Long> {
}
