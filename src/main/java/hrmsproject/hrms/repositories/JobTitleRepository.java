package hrmsproject.hrms.repositories;


import hrmsproject.hrms.entities.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleRepository extends JpaRepository<JobTitle,Integer> {
    JobTitle getById(int id);
    JobTitle getByTitle(String title);
}
