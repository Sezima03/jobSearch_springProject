package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam("name") String name,
                                         @RequestParam("surname") String surname,
                                         @RequestParam("age") int age,
                                         @RequestParam("email") String email,
                                         @RequestParam("password") String password,
                                         @RequestParam("phone_number")  String phone_number,
                                         @RequestParam("account_type") String account_type){

        //TODO logic

        return ResponseEntity.ok(register(name, surname, age, email, password, phone_number, account_type).getBody());
    }


}
