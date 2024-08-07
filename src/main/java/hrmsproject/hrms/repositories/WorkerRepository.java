package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.concretes.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    Worker getById(int id);
}
