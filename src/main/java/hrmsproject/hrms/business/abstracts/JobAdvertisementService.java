package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.JobAdvertisement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobAdvertisementService {
    Result add(JobAdvertisement jobAdvertisement);
    Result delete(int id);
    Result update(JobAdvertisement jobAdvertisement);
    Result changeActiveStatus(int id);
    DataResult<List<JobAdvertisement>> getAll();
    DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisements();
    DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsOrderByAsc();
    DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsOrderByDesc();
    DataResult<List<JobAdvertisement>> getAllJobAdvertisementsByCompanyName(String companyName);
 }
