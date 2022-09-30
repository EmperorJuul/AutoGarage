package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Onderdeel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnderdeelRepository extends JpaRepository<Onderdeel, Long> {
}
