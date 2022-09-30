package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Bon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonRepository extends JpaRepository<Bon, Long> {
}
