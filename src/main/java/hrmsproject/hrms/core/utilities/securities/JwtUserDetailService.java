package hrmsproject.hrms.core.utilities.securities;

import hrmsproject.hrms.repositories.UserRepository;
import hrmsproject.hrms.entities.concretes.User;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return JwtUserDetails.create(user);
    }

    public JwtUserDetails loadUserById(int id)
    {
        User user = userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
