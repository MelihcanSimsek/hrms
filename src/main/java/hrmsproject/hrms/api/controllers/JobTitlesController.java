package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.JobTitleService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobtitles/")
public class JobTitlesController {
    private JobTitleService service;

    @Autowired
    public JobTitlesController(JobTitleService jobTitleService) {
        this.service = jobTitleService;

    }

    @PostMapping("add")
    public Result add(@RequestBody JobTitle jobTitle)
    {

        return  this.service.add(jobTitle);
    }

    @PutMapping("update")
    public Result update(@RequestBody JobTitle jobTitle)
    {
        return this.service.update(jobTitle);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestParam int id)
    {
        return this.service.delete(id);
    }


    @GetMapping("getAll")
    public DataResult<List<JobTitle>> getAll()
    {
        return this.service.getAll();
    }
}
