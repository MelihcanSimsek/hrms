package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.UserService;
import hrmsproject.hrms.core.utilities.results.Result;
import hrmsproject.hrms.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
public class UsersController {
    private UserService service;

    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Result add(@RequestBody User user)
    {
        return this.service.add(user);
    }



}
