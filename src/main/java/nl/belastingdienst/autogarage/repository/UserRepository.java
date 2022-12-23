package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
