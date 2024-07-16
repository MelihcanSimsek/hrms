package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
