package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
    Candidate getByEmail(String email);
    Candidate getById(int id);
    Candidate getByIdentityNumber(String identityNumber);
}
