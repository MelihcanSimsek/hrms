package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.Confirmation;
import org.springframework.stereotype.Service;

@Service
public interface ConfirmationService {
    Result add(Confirmation confirmation);
}
