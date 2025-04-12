package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.ResumeService;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("resume")
@RequiredArgsConstructor
public class ResumeController {

    private final UserService userService;
    private final ResumeService resumeService;


    @GetMapping("allResume")
    public String allResume(Model model){
        List<ResumeDto> resumeDtos =resumeService.getAllResume();
        model.addAttribute("resumes", resumeDtos);
        return "list/allresume";
    }

    @GetMapping("update/{id}")
    public String updateResume(@PathVariable Long id, Model model){
        User userDto = userService.getById(id);
        model.addAttribute("user", userDto);
        return "temp/update";
    }
}
