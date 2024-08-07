package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.CandidateService;
import hrmsproject.hrms.business.abstracts.VerificationService;
import hrmsproject.hrms.core.adapters.mail.MailService;
import hrmsproject.hrms.core.adapters.MernisVerification;
import hrmsproject.hrms.core.utilities.results.*;
import hrmsproject.hrms.entities.concretes.Candidate;
import hrmsproject.hrms.entities.concretes.User;
import hrmsproject.hrms.entities.concretes.Verification;
import hrmsproject.hrms.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateManager implements CandidateService {
    private CandidateRepository repository;
    private MailService mailService;
    private VerificationService verificationService;

    @Autowired
    public CandidateManager(CandidateRepository repository, MailService mailService, VerificationService verificationService) {
        this.repository = repository;
        this.mailService = mailService;
        this.verificationService = verificationService;
    }

    @Override
    public Result add(Candidate candidate) {
        if(!MernisVerification.checkUserIsRealPerson(candidate.getIdentityNumber())) return new ErrorResult("Identity number is not valid");
        if(this.repository.getByIdentityNumber(candidate.getIdentityNumber()) != null) return new ErrorResult("User identity number already exists");
        if(this.repository.getByEmail(candidate.getEmail()) != null) return new ErrorResult("User email already exists");
        this.repository.save(candidate);
        Candidate savedCandidate = this.repository.getByEmail(candidate.getEmail());
        String code = this.verificationService.add(new Verification(0,"",false,null,new User(savedCandidate.getId(), savedCandidate.getEmail(), savedCandidate.getPassword(),savedCandidate.getCreatedAt(),savedCandidate.getUpdatedAt()))).getData();
        this.mailService.sendVerifyMail(candidate.getEmail(),"Lorem ipsum dolor sit amet",code);
        return  new SuccessResult("Candidate added");
    }

    @Override
    public Result delete(int id) {
        DataResult<Candidate> candidateDataResult = this.getById(id);
        if(!candidateDataResult.isSuccess()) return new ErrorResult(candidateDataResult.getMessage());
        this.repository.delete(candidateDataResult.getData());
        return new SuccessResult("Candidate deleted");
    }

    @Override
    public Result update(Candidate candidate) {
        DataResult<Candidate> candidateDataResult = this.getById(candidate.getId());
        if(!candidateDataResult.isSuccess()) return new ErrorResult(candidateDataResult.getMessage());
        Candidate updatedCandidate = candidateDataResult.getData();
        updatedCandidate.setBirthYear(candidate.getBirthYear());
        updatedCandidate.setFirstName(candidate.getFirstName());
        updatedCandidate.setEmail(candidate.getEmail());
        updatedCandidate.setLastName(candidate.getLastName());
        updatedCandidate.setPassword(candidate.getPassword());
        this.repository.save(updatedCandidate);
        return  new SuccessResult("Candidate updated");
    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<List<Candidate>>(this.repository.findAll(),"Candidate Listed");
    }

    private DataResult<Candidate> getById(int id)
    {
        Candidate candidate =  this.repository.getById(id);
        if(candidate == null) return new ErrorDataResult<Candidate>("Candidate does not exists");
        return new SuccessDataResult<Candidate>(candidate);
    }
}
