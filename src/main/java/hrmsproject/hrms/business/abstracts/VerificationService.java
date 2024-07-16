package hrmsproject.hrms.business.abstracts;


import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.Verification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VerificationService {
    Result verify(String guid);
    DataResult<List<Verification>> getAll();
    DataResult<String> add(Verification verification);
}
