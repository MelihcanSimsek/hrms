package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.JobAdvertisementService;
import hrmsproject.hrms.core.utilities.results.*;
import hrmsproject.hrms.entities.concretes.JobAdvertisement;
import hrmsproject.hrms.repositories.JobAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
    private JobAdvertisementRepository repository;

    @Autowired
    public JobAdvertisementManager(JobAdvertisementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
        this.repository.save(jobAdvertisement);
        return new SuccessResult("Job advertisement added");
    }

    @Override
    public Result delete(@RequestParam int id) {
        JobAdvertisement advertisement = this.repository.getById(id);
        if(advertisement == null) return new ErrorResult("Advertisement does not exists");
        this.repository.delete(advertisement);
        return new SuccessResult("Advertisement deleted");
    }

    @Override
    public Result update(@RequestBody JobAdvertisement jobAdvertisement) {
        JobAdvertisement advertisement = this.repository.getById(jobAdvertisement.getId());
        if(advertisement == null) return new ErrorResult("Advertisement does not exists");
        advertisement.setMinSalary(jobAdvertisement.getMinSalary());
        advertisement.setMaxSalary(jobAdvertisement.getMaxSalary());
        advertisement.setDescription(jobAdvertisement.getDescription());
        advertisement.setStartDate(jobAdvertisement.getStartDate());
        advertisement.setEndDate(jobAdvertisement.getEndDate());
        advertisement.setFreePositionAmount(jobAdvertisement.getFreePositionAmount());
        advertisement.setJobTitle(jobAdvertisement.getJobTitle());
        advertisement.setEmployer(jobAdvertisement.getEmployer());
        advertisement.setCity(jobAdvertisement.getCity());
        this.repository.save(advertisement);
        return new SuccessResult("Advertisement updated");
    }

    @Override
    public Result changeActiveStatus(@RequestParam int id) {
        JobAdvertisement advertisement = this.repository.getById(id);
        if(advertisement == null) return new ErrorResult("Advertisement does not exists");
        advertisement.setStatus(false);
        this.repository.save(advertisement);
        return new SuccessResult("Advertisement deactivated");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.repository.findAll(),"Job advertisement listed");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisements() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.repository.getByStatus(true),"Active job advertisement listed");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsOrderByAsc() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.repository.getByStatusOrderByEndDateAsc(true),"Active job advertisement listed Order by Asc");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsOrderByDesc() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.repository.getByStatusOrderByEndDateDesc(true),"Active job advertisement listed Order by Desc");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAllJobAdvertisementsByCompanyName(String companyName) {
        return new SuccessDataResult<List<JobAdvertisement>>(this.repository.getByEmployer_CompanyName(companyName),"Active job advertisement listed Order by CompanyName");
    }
}
