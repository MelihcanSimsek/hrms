package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
    City getById(int id);
}
