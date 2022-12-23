package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
