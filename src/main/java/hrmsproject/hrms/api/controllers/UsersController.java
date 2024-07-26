package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.UserService;
import hrmsproject.hrms.core.utilities.results.ErrorDataResult;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users/")
public class UsersController {
    private UserService service;

    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(this.service.add(user));
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

}
