package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Result add(User user);
    DataResult<User> getUserByEmail(String email);
}
