package hrmsproject.hrms.business.abstracts;

import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.concretes.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    Result add(City city);
    Result update(City city);
    Result delete(int id);
    DataResult<List<City>> getAll();
}
