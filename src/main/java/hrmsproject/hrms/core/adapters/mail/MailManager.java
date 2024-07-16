package hrmsproject.hrms.core.adapters.mail;

import hrmsproject.hrms.core.utilities.results.ErrorResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;


@Service
public class MailManager implements MailService {

    @Override
    public Result sendVerifyMail(String email, String mailContent,String code) {
        if(email.isBlank() || mailContent.isBlank())  new ErrorResult("Mail failed");
        System.out.println("Mailing to "+email+" | Mail content: "+mailContent+"| Code: "+code);
        return new SuccessResult("Mail sended");
    }

}
