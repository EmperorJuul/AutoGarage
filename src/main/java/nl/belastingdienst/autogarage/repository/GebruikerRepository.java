package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GebruikerRepository extends JpaRepository<User, String> {
}
