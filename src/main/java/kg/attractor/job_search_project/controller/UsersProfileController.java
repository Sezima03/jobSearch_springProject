package kg.attractor.job_search_project.controller;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersProfileController {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final UserService userService;
    private final ResponsesApplicantService responsesApplicantService;

    @GetMapping("profileApplicant")
    public String showProfileApplicant(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        String username = auth.getName();
        User user =userService.findUserByUsername(username);
        if (user == null) {
            return "redirect:/auth/login";
        }
        List<ResumeDto> resumeDtos =resumeService.getAllResumeByUserId(user.getId());
        int responded = responsesApplicantService.countRespondedApplicantByUserId(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("resumes", resumeDtos);
        model.addAttribute("count", responded);
        return "personalAccount/profileApplicant";
    }

    @GetMapping("profileEmp")
    public String showProfileEmployer(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        String username = auth.getName();
       User user = userService.findUserByUsername(username);
        if (user == null) {
            return "redirect:/auth/login";
        }
        List<VacancyDto> vacancies = vacancyService.getAllVacancyByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("vacancies", vacancies);
        return "personalAccount/profileEmployer";
    }

    @GetMapping("updateProfile/{id}")
    public String updateResume(@PathVariable Long id, Model model){
        User userDto = userService.getById(id);
        model.addAttribute("user", userDto);
        return "temp/update";
    }

    @PostMapping("updateProfile/{id}")
    public String updateResume(@PathVariable Long id, UserDto userDto){
        User user = userService.getById(id);
        if (user == null) {
            return "redirect:/users/updateProfile/{id}";
        }
        userService.updateProfile(userDto);
        return "redirect:/users/profileApplicant";
    }

    @GetMapping("updateProfileEmployer/{id}")
    public String updateProfileEmployer(@PathVariable Long id, Model model){
        User userDto = userService.getById(id);
        model.addAttribute("user", userDto);
        return "personalAccount/updateProfileEmployer";
    }
}