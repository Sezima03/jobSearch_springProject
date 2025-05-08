package kg.attractor.job_search_project.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @GetMapping("employer")
    public String registerFormForEmployer(Model model) {
        UserDto userDto = new UserDto();
        userDto.setAuthorityId(2L);
        model.addAttribute("userDto", userDto);
        return "registrations/employer";
    }

    @PostMapping("employer")
    public String registerFormForEmployer(@ModelAttribute("userDto")
                                              @Valid UserDto userDto,
                                          BindingResult bindingResult){
        if (userDto.getAuthorityId() == null) {
            userDto.setAuthorityId(2L);
        }
        if (!bindingResult.hasErrors()) {
            userService.registerUser(userDto);
            return "redirect:/auth/login";
        }
        return "registrations/employer";
    }

    @GetMapping("applicant")
    public String usersRegisterFormForApplicant(Model model) {
        UserDto userDto = new UserDto();
        userDto.setAuthorityId(1L);
        model.addAttribute("userDto",userDto);
        return "registrations/applicant";
    }

    @PostMapping("applicant")
    public String usersRegisterFormForApplicant(@ModelAttribute("userDto")
                                                @Valid UserDto userDto,
                                                BindingResult bindingResult) {

        if (userDto.getAuthorityId() == null) {
            userDto.setAuthorityId(1L);
        }
        if (!bindingResult.hasErrors()) {
            userService.registerUser(userDto);
            return "redirect:/auth/login";
        }
        return "registrations/applicant";
    }
}