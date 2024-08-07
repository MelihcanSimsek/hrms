package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.concretes.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepository extends JpaRepository<Confirmation,Integer> {
}
