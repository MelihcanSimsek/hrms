package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification,Integer> {

    Verification getByCode(String code);
}