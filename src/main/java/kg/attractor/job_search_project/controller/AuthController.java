package kg.attractor.job_search_project.controller;

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

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User user1 =authServiceImpl.createAccount(user.getName(), user.getSurname(), user.getAge(), user.getEmail(), user.getPassword(), user.getPhone_number(), user.getAccount_type());
        //TODO logic

        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


}
