package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.CandidateService;
import hrmsproject.hrms.core.utilities.results.DataResult;
import hrmsproject.hrms.core.utilities.results.ErrorDataResult;
import hrmsproject.hrms.entities.concretes.Candidate;
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
@RequestMapping("/api/candiadates/")
public class CandidatesController {
    private CandidateService service;

    @Autowired
    public CandidatesController(CandidateService service) {
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
    public ResponseEntity<?> add(@Valid @RequestBody Candidate candidate)
    {
        return ResponseEntity.ok(this.service.add(candidate));
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Candidate candidate)
    {
        return ResponseEntity.ok(this.service.update(candidate));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam int id)
    {
      return ResponseEntity.ok(this.service.delete(id));
    }

    @GetMapping("get-all")
    public DataResult<List<Candidate>> getAll()
    {
        return this.service.getAll();
    }

}
