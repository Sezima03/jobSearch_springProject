package kg.attractor.job_search_project.controller;
import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.UserService;
import kg.attractor.job_search_project.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> isEmailTaken(@RequestBody @Valid UserDto userDto){
        String users=userService.registerUser(userDto);
        if (users.equals("Email уже существует")){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(users);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("name/{name}")
    public List<UserDto> getSearchByName(@PathVariable String name){

        return userService.getSearchByName(name);
    }
    @GetMapping("number/{number}")
    public List<UserDto> getSearchByNumber(@PathVariable String number){
        return userService.getSearchByNumber(number);
    }
    @GetMapping("/email/{email}")
    public List<UserDto> getSearchByEmail(@PathVariable String email){
        return userService.getSearchByEmail(email);
    }

    @GetMapping("responded/{respondedId}")
    public List<VacancyDto>  vacancyByResponded(@PathVariable("respondedId") Long applicantId) {
        return userService.getRespondedToVacancy(applicantId);
    }
}
