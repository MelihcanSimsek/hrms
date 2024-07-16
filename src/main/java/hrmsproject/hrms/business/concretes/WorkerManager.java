package hrmsproject.hrms.business.concretes;
import hrmsproject.hrms.business.abstracts.WorkerService;
import hrmsproject.hrms.core.utilities.results.*;
import hrmsproject.hrms.entities.Worker;
import hrmsproject.hrms.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerManager implements WorkerService {
    private WorkerRepository repository;

    @Autowired
    public WorkerManager(WorkerRepository workerRepository) {
        this.repository = workerRepository;
    }

    @Override
    public Result add(Worker worker) {
        this.repository.save(worker);

        return new SuccessResult("Worker added");
    }

    @Override
    public Result delete(int id) {
       DataResult<Worker> workerDataResult = this.getById(id);
       if(!workerDataResult.isSuccess()) return new ErrorResult(workerDataResult.getMessage());
       this.repository.delete(workerDataResult.getData());
       return new SuccessResult("Worker deleted");
    }

    @Override
    public Result update(Worker worker) {
        DataResult<Worker> workerDataResult = this.getById(worker.getId());
        if(!workerDataResult.isSuccess()) return new ErrorResult(workerDataResult.getMessage());
        Worker updatedWorker = workerDataResult.getData();
        updatedWorker.setFirstName(worker.getFirstName());
        updatedWorker.setEmail(worker.getEmail());
        updatedWorker.setLastName(worker.getLastName());
        updatedWorker.setPassword(worker.getPassword());
        this.repository.save(updatedWorker);
        return new SuccessResult("Worker updated");
    }

    @Override
    public DataResult<List<Worker>> getAll() {
        return new SuccessDataResult<List<Worker>>(this.repository.findAll());
    }

    private DataResult<Worker> getById(int id)
    {
        Worker worker = this.repository.getById(id);
        if(worker == null) return new ErrorDataResult<Worker>("Worker does not exists");
        return new SuccessDataResult<Worker>(worker);
    }
}
