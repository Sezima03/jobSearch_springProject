package kg.attractor.job_search_project.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.ResumeService;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("created")
    public String createdResume(Model model, ResumeDto resumeDto, EducationInfoDto educationInfoDto, WorkExperienceInfoDto workExperienceInfoDto){
        model.addAttribute("resumeDto", resumeDto);
        model.addAttribute("educationfoDto", educationInfoDto);
        model.addAttribute("work", workExperienceInfoDto);
        return "resumeAndVacancy/createdResume";
    }

    @PostMapping("created")
    public String createdResume(@ModelAttribute("resumeDto") @Valid ResumeDto resumeDto,
                                BindingResult bindingResultResume,
                                @ModelAttribute("educationfoDto") @Valid EducationInfoDto educationInfoDto,
                                BindingResult edu,
                                @ModelAttribute("work") @Valid WorkExperienceInfoDto workExperienceInfoDto,
                                BindingResult bindingResult){

        if (bindingResultResume.hasErrors() || edu.hasErrors() || bindingResult.hasErrors()) {
            return "resumeAndVacancy/createdResume";
        }

        resumeDto.setEducationInfo(List.of(educationInfoDto));
        resumeDto.setWorkExperienceInfo(List.of(workExperienceInfoDto));
        resumeService.getCreateResume(resumeDto);
        return "redirect:/";


    }


    @GetMapping("editResume/{resumeId}")
    public String editResume(Model model,
                             @PathVariable Long resumeId,
                             ResumeDto resumeDto,
                             EducationInfoDto educationInfoDto,
                             WorkExperienceInfoDto workExperienceInfoDto){
        model.addAttribute("resumes", resumeService.getFindResumeById(resumeId));
        model.addAttribute("resumeDto", resumeDto);
        model.addAttribute("educationfoDto", educationInfoDto);
        model.addAttribute("work", workExperienceInfoDto);
        return "resumeAndVacancy/editResume";
    }

    @PostMapping("editResume/{resumeId}")
    public String editResume(@PathVariable Long resumeId,
                             @ModelAttribute("resumeDto") @Valid ResumeDto resumeDto,
                             BindingResult resumeBindingResult,
                             @ModelAttribute("educationfoDto") @Valid EducationInfoDto educationInfoDto,
                             BindingResult edu,
                             @ModelAttribute("work") @Valid WorkExperienceInfoDto workExperienceInfoDto,
                             BindingResult workBindingResult,
                             Model model){
        model.addAttribute("resumes", resumeService.getFindResumeById(resumeId));

        if (resumeBindingResult.hasErrors() || edu.hasErrors() || workBindingResult.hasErrors()){
            return "resumeAndVacancy/editResume";
        }

        resumeDto.setEducationInfo(List.of(educationInfoDto));
        resumeDto.setWorkExperienceInfo(List.of(workExperienceInfoDto));

        resumeService.getUpdateResume(resumeId, resumeDto);
        return "redirect:/";
    }
}
