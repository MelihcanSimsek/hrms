package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {
    Result add(Candidate candidate);
    Result delete(int id);
    Result update(Candidate candidate);
    DataResult<List<Candidate>> getAll();
}
