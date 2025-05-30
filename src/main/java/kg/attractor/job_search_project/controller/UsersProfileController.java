package kg.attractor.job_search_project.controller;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.UserImage;
import kg.attractor.job_search_project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersProfileController {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final UserService userService;
    private final ResponsesApplicantService responsesApplicantService;
    private final ImageService imageService;

    @GetMapping("profileApplicant")
    public String showProfileApplicant(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        String username = auth.getName();
        User user =userService.findUserByUsername(username);
        if (user == null) {
            return "redirect:/auth/login";
        }
        if (page < 0) {
            page = 0;
        }
        UserImage userImage = imageService.getImageDtoByUserId(user.getId());

        Page<ResumeDto> resumeDtos =resumeService.getAllResumeByUserId(user.getId(), page, size);
        int responded = responsesApplicantService.countRespondedApplicantByUserId(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("resumes", resumeDtos);
        model.addAttribute("count", responded);
        model.addAttribute("userImageDto", userImage);
        model.addAttribute("currentPage", resumeDtos.getNumber());
        model.addAttribute("totalPages", resumeDtos.getTotalPages());
        return "personalAccount/profileApplicant";
    }

    @GetMapping("profileEmp")
    public String showProfileEmployer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        String username = auth.getName();
       User user = userService.findUserByUsername(username);
        if (user == null) {
            return "redirect:/auth/login";
        }

        if (page < 0) {
            page = 0;
        }
        UserImage userImage = imageService.getImageDtoByUserId(user.getId());


        Page<VacancyDto> vacancies = vacancyService.getAllVacancyByUserId(user.getId(), page, size);
        model.addAttribute("user", user);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("userImageDto", userImage);
        model.addAttribute("currentPage", vacancies.getNumber());
        model.addAttribute("totalPages", vacancies.getTotalPages());
        return "personalAccount/profileEmployer";
    }

    @GetMapping("updateProfile/{id}")
    public String updateResume(@PathVariable Long id, Model model){
        Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName();
        User user = userService.findUserByUsername(authName);
        if (user == null) {
            return "redirect:/auth/login";
        }
        User userDto = userService.getById(id);
        model.addAttribute("user", userDto);
        return "personalAccount/updateProfileApplicant";
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

    @PostMapping("updateProfileEmployer/{id}")
    public String updateProfileEmployer(@PathVariable Long id, UserDto userDto){
        User user = userService.getById(id);
        if (user == null) {
            return "redirect:/users/updateProfileEmployer/{id}";
        }
        userService.updateProfile(userDto);
        return "redirect:/users/profileEmp";
    }
}