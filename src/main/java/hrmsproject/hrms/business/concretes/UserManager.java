package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.UserService;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.core.utilities.results.SuccessResult;
import hrmsproject.hrms.entities.User;
import hrmsproject.hrms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager  implements UserService {
    private UserRepository repository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public Result add(User user) {
        this.repository.save(user);
        return new SuccessResult("User added");
    }
}
