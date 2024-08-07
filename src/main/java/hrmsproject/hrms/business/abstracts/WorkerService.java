package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.Worker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkerService {
    Result add(Worker worker);
    Result delete(int id);
    Result update(Worker worker);
    DataResult<List<Worker>> getAll();
}
