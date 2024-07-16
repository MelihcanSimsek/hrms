package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.JobTitleService;
import hrmsproject.hrms.core.utilities.results.*;
import hrmsproject.hrms.entities.JobTitle;
import hrmsproject.hrms.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobTitleManager implements JobTitleService {
    private JobTitleRepository repository;

    @Autowired
    public JobTitleManager(JobTitleRepository jobTitleRepository) {
        this.repository = jobTitleRepository;
    }

    @Override
    public DataResult<List<JobTitle>> getAll() {
        return new SuccessDataResult<List<JobTitle>>(this.repository.findAll(),"Title Listed");
    }

    @Override
    public Result add(JobTitle jobTitle) {
        if(this.checkJobTitleDuplicated(jobTitle.getTitle())) return new ErrorResult("Job title already exists");
        this.repository.save(jobTitle);
        return  new SuccessResult("Job title added");
    }

    @Override
    public Result delete(int id) {
        DataResult<JobTitle> result = this.getById(id);
        if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        this.repository.delete(result.getData());
        return new SuccessResult("Job title deleted");
    }

    @Override
    public Result update(JobTitle jobTitle) {
        DataResult<JobTitle> result = this.getById(jobTitle.getId());
        if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        JobTitle updatedJobTitle = result.getData();
        updatedJobTitle.setTitle(jobTitle.getTitle());
        this.repository.save(updatedJobTitle);
        return new SuccessResult("Job title updated");
    }

    private DataResult<JobTitle> getById(int id)
    {
        JobTitle jobTitle = this.repository.getById(id);
        if(jobTitle == null) return new ErrorDataResult<JobTitle>("Job title does not exists");
        return new SuccessDataResult<JobTitle>(jobTitle);
    }

    private  boolean checkJobTitleDuplicated(String title)
    {
        JobTitle jobTitle =  this.repository.getByTitle(title);
        return jobTitle != null;
    }
}
