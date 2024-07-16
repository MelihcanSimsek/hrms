package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.ConfirmationService;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.core.utilities.results.SuccessResult;
import hrmsproject.hrms.entities.Confirmation;
import hrmsproject.hrms.repositories.ConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationManager implements ConfirmationService {
    private ConfirmationRepository repository;

    @Autowired
    public ConfirmationManager(ConfirmationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result add(Confirmation confirmation) {
        this.repository.save(confirmation);
        return new SuccessResult("Confirmation from " + confirmation.getWorker().getFirstName() +" to "+confirmation.getEmployer().getCompanyName());
    }
}
