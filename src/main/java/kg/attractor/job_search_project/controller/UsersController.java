package kg.attractor.job_search_project.controller;
import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.ResumeService;
import kg.attractor.job_search_project.service.UserService;
import kg.attractor.job_search_project.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UsersController {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "temp/register";
    }

    @PostMapping
    public String registerForm(@Valid UserDto userDto,
                               BindingResult bindingResult,
                               Model model){
        if (!bindingResult.hasErrors()) {
            userService.registerUser(userDto);
            return "redirect:/";

        }
        model.addAttribute("userDto", userDto);
        return "temp/register";
    }

    @GetMapping("profileApp")
    public String showProfileApplicant(Model model) {
        List<ResumeDto> resumeDtos =resumeService.getAllResume();
        model.addAttribute("resumes", resumeDtos);
        return "temp/resumeApp";
    }

    @GetMapping("profileEmp")
    public String showProfileEmployer(Model model) {
        List<VacancyDto> vacancies = vacancyService.getVacancy();

        model.addAttribute("vacancies", vacancies);
        return "temp/vacancy";
    }

}
