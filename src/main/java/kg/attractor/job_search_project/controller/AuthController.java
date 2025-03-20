package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    @PostMapping("register")
    public ResponseEntity<String> isEmailTaken(@RequestBody User user){
        String users=authServiceImpl.registerUser(user);
        if (users.equals("Email уже существует")){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(users);
        }
        return ResponseEntity.ok(users);
    }


}
