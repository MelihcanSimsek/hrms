package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.EmployerService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.ErrorDataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.Employer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employers/")
public class EmployersController {
    private EmployerService service;

    @Autowired
    public EmployersController(EmployerService service) {
        this.service = service;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String,String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors())
        {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ErrorDataResult<Object>(validationErrors,"Validation Errors");
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody Employer employer)
    {
        return  ResponseEntity.ok(this.service.add(employer));
    }

    @GetMapping("get-all")
    public DataResult<List<Employer>> getAll()
    {
        return this.service.getAll();
    }

}
