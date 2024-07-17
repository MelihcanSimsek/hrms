package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.JobAdvertisementService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.JobAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements/")
public class JobAdvertisementsController {
    private JobAdvertisementService service;

    @Autowired
    public JobAdvertisementsController(JobAdvertisementService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Result add(@RequestBody JobAdvertisement advertisement)
    {
        return this.service.add(advertisement);
    }

    @PutMapping("update")
    public Result update(@RequestBody JobAdvertisement advertisement)
    {
        return this.service.update(advertisement);
    }


    @DeleteMapping("delete")
    public Result delete(@RequestParam int id)
    {
        return this.service.delete(id);
    }

    @GetMapping("get-all")
    public DataResult<List<JobAdvertisement>> getAll()
    {
        return this.service.getAll();
    }

    @GetMapping("get-all/active-advertisement/default")
    public DataResult<List<JobAdvertisement>> getAllActiveAdvertisement()
    {
        return this.service.getAllActiveJobAdvertisements();
    }

    @GetMapping("get-all/active-advertisement/by-asc")
    public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsOrderByAsc()
    {
        return this.service.getAllActiveJobAdvertisementsOrderByAsc();
    }

    @GetMapping("get-all/active-advertisement/by-desc")
    public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsOrderByDesc()
    {
        return this.service.getAllActiveJobAdvertisementsOrderByDesc();
    }

    @GetMapping("get-all/active-advertisement/by-company-name")
    public DataResult<List<JobAdvertisement>> getAllJobAdvertisementsByCompanyName(@RequestParam String companyName)
    {
        return this.service.getAllJobAdvertisementsByCompanyName(companyName);
    }

    @PostMapping("change-active-state")
    public Result changeActiveState(@RequestParam int id)
    {
        return this.service.changeActiveStatus(id);
    }




}
