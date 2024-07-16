package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.EmployerService;
import hrmsproject.hrms.business.abstracts.VerificationService;
import hrmsproject.hrms.core.adapters.mail.MailService;
import hrmsproject.hrms.core.utilities.results.*;
import hrmsproject.hrms.entities.Employer;
import hrmsproject.hrms.entities.User;
import hrmsproject.hrms.entities.Verification;
import hrmsproject.hrms.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {
    private EmployerRepository repository;
    private MailService mailService;
    private VerificationService verificationService;

    @Autowired
    public EmployerManager(EmployerRepository repository, MailService mailService, VerificationService verificationService) {
        this.repository = repository;
        this.mailService = mailService;
        this.verificationService = verificationService;
    }

    @Override
    public Result add(Employer employer) {
        if(this.checkEmployerExists(employer.getEmail())) return new ErrorResult("Employer already exists");
        this.repository.save(employer);
        Employer savedEmployer = this.repository.getByEmail(employer.getEmail());
        String code = this.verificationService.add(new Verification(0,"",false,null,new User(savedEmployer.getId(), savedEmployer.getEmail(), savedEmployer.getPassword()))).getData();
        this.mailService.sendVerifyMail(employer.getEmail(),"Lorem ipsum dolor sit amet",code);
        return new SuccessResult("Employer added");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.repository.findAll(),"Employers Listed");
    }

    private boolean checkEmployerExists(String email)
    {
        Employer employer = this.repository.getByEmail(email);
        return employer != null;
    }
}
