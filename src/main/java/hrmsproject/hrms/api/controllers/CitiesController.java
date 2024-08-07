package hrmsproject.hrms.api.controllers;


import hrmsproject.hrms.business.abstracts.CityService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.City;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities/")
public class CitiesController {
    private CityService service;

    public CitiesController(CityService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Result add(@RequestBody City city)
    {
        return this.service.add(city);
    }

    @PutMapping("update")
    public Result update(@RequestBody City city)
    {
        return this.service.update(city);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestParam int id)
    {
        return this.service.delete(id);
    }

    @GetMapping("get-all")
    public DataResult<List<City>> getAll()
    {
        return this.service.getAll();
    }
}
