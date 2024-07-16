package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.VerificationService;
import hrmsproject.hrms.core.utilities.results.*;
import hrmsproject.hrms.entities.Verification;
import hrmsproject.hrms.repositories.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VerificationManager implements VerificationService {
    private VerificationRepository repository;

    @Autowired
    public VerificationManager(VerificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result verify(String code) {
        Verification verification = this.repository.getByCode(code);
        if(verification == null) return new ErrorResult("Verification code is not valid");
        verification.setVerified(true);
        verification.setVerificationDate(new Date());
        this.repository.save(verification);
        return new SuccessResult("Verification Completed");
    }

    @Override
    public DataResult<List<Verification>> getAll() {
        return new SuccessDataResult<List<Verification>>(this.repository.findAll(),"Verifications Listed");
    }

    @Override
    public DataResult<String> add(Verification verification) {
        String verificationCode = this.generateCode();
        verification.setCode(verificationCode);
        this.repository.save(verification);
        return new SuccessDataResult<String>(verificationCode,"Verification added");
    }

    private String generateCode()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
