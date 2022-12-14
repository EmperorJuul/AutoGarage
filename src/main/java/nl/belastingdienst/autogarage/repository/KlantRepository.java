package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlantRepository extends JpaRepository<Customer, Long> {
}
