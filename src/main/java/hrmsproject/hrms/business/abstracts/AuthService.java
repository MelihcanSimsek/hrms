package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.dtos.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    Result registerEmployer(RegisterEmployerDto registerEmployerDto);
    Result registerCandidate(RegisterCandidateDto registerCandidateDto);
    Result registerWorker(RegisterWorkerDto registerWorkerDto);
    DataResult<LoginResponse> login(LoginDto loginDto);
}
