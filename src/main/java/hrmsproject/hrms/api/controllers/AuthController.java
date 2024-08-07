package hrmsproject.hrms.api.controllers;

import hrmsproject.hrms.business.abstracts.AuthService;
import hrmsproject.hrms.entities.dtos.LoginDto;
import hrmsproject.hrms.entities.dtos.RegisterCandidateDto;
import hrmsproject.hrms.entities.dtos.RegisterEmployerDto;
import hrmsproject.hrms.entities.dtos.RegisterWorkerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginDto loginDto)
    {
        return ResponseEntity.ok(this.authService.login(loginDto));
    }

    @PostMapping("/registerWorker")
    public ResponseEntity<?> registerWorker(@RequestBody RegisterWorkerDto registerWorkerDto)
    {
        return ResponseEntity.ok(this.authService.registerWorker(registerWorkerDto));
    }

    @PostMapping("/registerEmployer")
    public ResponseEntity<?> registerEmployer(@RequestBody RegisterEmployerDto registerEmployerDto)
    {
        return ResponseEntity.ok(this.authService.registerEmployer(registerEmployerDto));
    }

    @PostMapping("/registerCandidate")
    public ResponseEntity<?> registerCandidate(@RequestBody RegisterCandidateDto registerCandidateDto)
    {
        return ResponseEntity.ok(this.authService.registerCandidate(registerCandidateDto));
    }


}
