package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.*;
import hrmsproject.hrms.core.utilities.results.*;
import hrmsproject.hrms.core.utilities.securities.JwtTokenProvider;
import hrmsproject.hrms.core.utilities.securities.JwtUserDetailService;
import hrmsproject.hrms.entities.concretes.Candidate;
import hrmsproject.hrms.entities.concretes.Employer;
import hrmsproject.hrms.entities.concretes.User;
import hrmsproject.hrms.entities.concretes.Worker;
import hrmsproject.hrms.entities.converters.CandidateConverter;
import hrmsproject.hrms.entities.converters.EmployerConverter;
import hrmsproject.hrms.entities.converters.WorkerConverter;
import hrmsproject.hrms.entities.dtos.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {
    private final JwtUserDetailService jwtService;
    private final EmployerService employerService;
    private final CandidateService candidateService;
    private final WorkerService workerService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final WorkerConverter workerConverter;
    private final CandidateConverter candidateConverter;
    private final EmployerConverter employerConverter;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthManager(JwtUserDetailService jwtService,
                       EmployerService employerService,
                       CandidateService candidateService,
                       WorkerService workerService,
                       UserService userService,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       WorkerConverter workerConverter,
                       CandidateConverter candidateConverter,
                       EmployerConverter employerConverter, JwtTokenProvider jwtTokenProvider) {
        this.jwtService = jwtService;
        this.employerService = employerService;
        this.candidateService = candidateService;
        this.workerService = workerService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.workerConverter = workerConverter;
        this.candidateConverter = candidateConverter;
        this.employerConverter = employerConverter;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Result registerEmployer(RegisterEmployerDto registerEmployerDto) {
        if(checkUserExists(registerEmployerDto.getEmail())) return new ErrorResult("User already exists");

        Employer employer = employerConverter.registerEmployerDtoToEmployer(registerEmployerDto);
        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
        return employerService.add(employer);
    }

    @Override
    public Result registerCandidate(RegisterCandidateDto registerCandidateDto) {
        if(checkUserExists(registerCandidateDto.getEmail())) return new ErrorResult("User already exists");

        Candidate candidate = candidateConverter.registerCandidateDtoToCandidate(registerCandidateDto);
        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
        return candidateService.add(candidate);
    }

    @Override
    public Result registerWorker(RegisterWorkerDto registerWorkerDto) {
        if(checkUserExists(registerWorkerDto.getEmail())) return new ErrorResult("User already exists");

        Worker worker = workerConverter.registerWorkerDtoToWorker(registerWorkerDto);
        worker.setPassword(passwordEncoder.encode(worker.getPassword()));
        return workerService.add(worker);
    }

    @Override
    public DataResult<LoginResponse> login(LoginDto loginDto) {
        if(!this.checkUserExists(loginDto.getEmail())) return new ErrorDataResult<>("email or password wrong");
        if(!this.checkUserPasswordCorrect(loginDto)) return new ErrorDataResult<>("email or password wrong");

       Authentication auth =  authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword())));
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtTokenProvider.getExpirationTime());

        return new SuccessDataResult<>(loginResponse, "Login Successfully");
    }

    private Boolean checkUserExists(String email)
    {
        User user = userService.getUserByEmail(email).getData();
        return user != null;
    }

    private Boolean checkUserPasswordCorrect(LoginDto loginDto)
    {
        User user = userService.getUserByEmail(loginDto.getEmail()).getData();
        return passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
    }

}
