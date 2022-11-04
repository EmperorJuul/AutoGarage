package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Afspraak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfspraakRepository extends JpaRepository<Afspraak, Long> {
}