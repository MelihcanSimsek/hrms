package hrmsproject.hrms.repositories;

import hrmsproject.hrms.entities.concretes.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement,Integer> {
    List<JobAdvertisement> getByStatus(boolean status);
    JobAdvertisement getById(int id);
    List<JobAdvertisement> getByStatusOrderByEndDateAsc(boolean status);
    List<JobAdvertisement> getByStatusOrderByEndDateDesc(boolean status);
    List<JobAdvertisement> getByEmployer_CompanyName(String companyName);
}
