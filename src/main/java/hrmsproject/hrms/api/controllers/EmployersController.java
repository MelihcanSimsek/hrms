package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.EmployerService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers/")
public class EmployersController {
    private EmployerService service;

    @Autowired
    public EmployersController(EmployerService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Result add(@RequestBody Employer employer)
    {
        return  this.service.add(employer);
    }

    @GetMapping("get-all")
    public DataResult<List<Employer>> getAll()
    {
        return this.service.getAll();
    }

}
