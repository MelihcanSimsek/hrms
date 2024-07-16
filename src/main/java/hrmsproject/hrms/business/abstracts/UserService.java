package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Result add(User user);

}
