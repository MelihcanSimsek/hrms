package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.VerificationService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verifications/")
public class VerificationsController {
    private VerificationService service;

    @Autowired
    public VerificationsController(VerificationService service) {
        this.service = service;
    }

    @PostMapping("verify")
    public Result verify(@RequestParam String guid)
    {
      return this.service.verify(guid);
    }

    @GetMapping("get-all")
    public DataResult<List<Verification>> getAll()
    {
        return this.service.getAll();
    }
}
