package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GebruikerRepository extends JpaRepository<Gebruiker, String> {
}
