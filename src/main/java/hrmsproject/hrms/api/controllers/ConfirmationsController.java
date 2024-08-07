package hrmsproject.hrms.api.controllers;


import hrmsproject.hrms.business.abstracts.ConfirmationService;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.Confirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/confirmations/")
public class ConfirmationsController {
    private ConfirmationService service;

    @Autowired
    public ConfirmationsController(ConfirmationService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Result add(@RequestBody Confirmation confirmation)
    {
        return this.service.add(confirmation);
    }

}
