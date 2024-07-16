package hrmsproject.hrms.core.adapters.mail;

import hrmsproject.hrms.core.utilities.results.Result;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    Result sendVerifyMail(String email, String mailContent, String code);

}
