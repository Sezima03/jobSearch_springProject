package kg.attractor.job_search_project.controller;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.UserImage;
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
    private final ImageService imageService;

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
        UserImage userImage = imageService.getImageDtoByUserId(user.getId());
        String imageName = userImage!=null? userImage.getFileName():null;
        List<ResumeDto> resumeDtos =resumeService.getAllResumeByUserId(user.getId());
        int responded = responsesApplicantService.countRespondedApplicantByUserId(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("resumes", resumeDtos);
        model.addAttribute("count", responded);
        model.addAttribute("userImageDto", imageName);
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
        UserImage userImage = imageService.getImageDtoByUserId(user.getId());

        String imageName = userImage!=null? userImage.getFileName():null;
        List<VacancyDto> vacancies = vacancyService.getAllVacancyByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("userImageDto", imageName);
        return "personalAccount/profileEmployer";
    }

    @GetMapping("updateProfile/{id}")
    public String updateResume(@PathVariable Long id, Model model){
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