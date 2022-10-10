package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Authoriteit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoriteitRepository extends JpaRepository<Authoriteit, Long> {
}
