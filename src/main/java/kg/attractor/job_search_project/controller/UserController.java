package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


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
}
