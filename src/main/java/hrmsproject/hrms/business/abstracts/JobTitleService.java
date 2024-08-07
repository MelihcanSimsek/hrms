package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.JobTitle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobTitleService {
    DataResult<List<JobTitle>> getAll();
    Result add(JobTitle jobTitle);
    Result delete(int id);
    Result update(JobTitle jobTitle);
}
