package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepository extends JpaRepository<Confirmation,Integer> {
}
