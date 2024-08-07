package hrmsproject.hrms.business.concretes;

import hrmsproject.hrms.business.abstracts.CityService;
import hrmsproject.hrms.core.utilities.results.*;
import hrmsproject.hrms.entities.concretes.City;
import hrmsproject.hrms.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {
    private CityRepository repository;

    @Autowired
    public CityManager(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result add(City city) {
        this.repository.save(city);
        return new SuccessResult("City Added");
    }

    @Override
    public Result update(City city) {
       DataResult<City> cityDataResult = this.getById(city.getId());
       if(!cityDataResult.isSuccess()) return new ErrorResult(cityDataResult.getMessage());
       City oldCity = cityDataResult.getData();
       oldCity.setName(city.getName());
       this.repository.save(oldCity);
       return new ErrorResult("City updated");
    }

    @Override
    public Result delete(int id) {
        DataResult<City> cityDataResult = this.getById(id);
        if(!cityDataResult.isSuccess()) return new ErrorResult(cityDataResult.getMessage());
        City city = cityDataResult.getData();
        this.repository.delete(city);
        return new SuccessResult("City deleted");
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.repository.findAll(),"City Listed");
    }

    private DataResult<City> getById(int id)
    {
        City city = this.repository.getById(id);
        if(city == null) return new ErrorDataResult<City>("City does not exists");
        return new SuccessDataResult<City>(city);
    }
}
