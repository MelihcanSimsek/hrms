package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.WorkerService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers/")
public class WorkersController {
    private WorkerService service;

    @Autowired
    public WorkersController(WorkerService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Result add(@RequestBody Worker worker)
    {
        return  this.service.add(worker);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestParam int id)
    {
        return this.service.delete(id);
    }

    @PutMapping("update")
    public Result update(@RequestBody Worker worker)
    {
        return this.service.update(worker);
    }

    @GetMapping("get-all")
    public DataResult<List<Worker>> getAll()
    {
        return  this.service.getAll();
    }


}
