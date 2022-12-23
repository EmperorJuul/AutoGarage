package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {
}
