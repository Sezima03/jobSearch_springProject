package kg.attractor.job_search_project.controller;


import kg.attractor.job_search_project.service.UserService;
import kg.attractor.job_search_project.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    @GetMapping("login")
    public String login(){
        return "temp/login";
    }

    @PostMapping("login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model){

        String result = userService.loginUser(email, password);

        if (result.equals("успешно")){
            return "redirect:/";
        }
        model.addAttribute("error", result);
        return "temp/login";
    }
}
