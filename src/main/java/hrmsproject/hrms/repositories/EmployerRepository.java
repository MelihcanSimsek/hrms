package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer,Integer> {
    Employer getByEmail(String email);
    Employer getById(int id);
}
