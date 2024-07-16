package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.CandidateService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candiadates/")
public class CandidatesController {
    private CandidateService service;

    @Autowired
    public CandidatesController(CandidateService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Result add(Candidate candidate)
    {
        return this.service.add(candidate);
    }

    @PutMapping("update")
    public Result update(@RequestBody Candidate candidate)
    {
        return this.service.update(candidate);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestParam int id)
    {
      return this.service.delete(id);
    }

    @GetMapping("get-all")
    public DataResult<List<Candidate>> getAll()
    {
        return this.service.getAll();
    }

}
