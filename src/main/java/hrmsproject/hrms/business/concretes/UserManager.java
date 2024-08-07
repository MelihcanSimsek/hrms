package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.UserService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.core.utilities.results.SuccessDataResult;
import hrmsproject.hrms.core.utilities.results.SuccessResult;
import hrmsproject.hrms.entities.concretes.User;
import hrmsproject.hrms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public DataResult<User> getUserByEmail(String email){
        User user =  this.repository.findByEmail(email);
        return new SuccessDataResult<>(user);
    }
}
