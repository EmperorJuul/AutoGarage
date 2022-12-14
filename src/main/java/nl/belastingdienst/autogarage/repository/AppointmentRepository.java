package nl.belastingdienst.autogarage.repository;

import nl.belastingdienst.autogarage.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}