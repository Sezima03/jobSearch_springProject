package kg.attractor.job_search_project.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.ResumeService;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
                                EducationInfoDto educationInfoDto,
                                WorkExperienceInfoDto workExperienceInfoDto){

        if (bindingResultResume.hasErrors()) {
            return "resumeAndVacancy/createdResume";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.findUserByUsername(username);

        resumeDto.setApplicantId(user.getId());

        resumeDto.setEducationInfo(List.of(educationInfoDto));
        resumeDto.setWorkExperienceInfo(List.of(workExperienceInfoDto));
        resumeService.getCreateResume(resumeDto);
        return "redirect:/users/profileApp";


    }


    //TODO ошибка 500
    //TODO реализовать отклики и функциональность откликнутся на вакансию
    //TODO если резюме false не должно появляться
    //TODO загрузка фотографий
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
                             EducationInfoDto educationInfoDto,
                             WorkExperienceInfoDto workExperienceInfoDto,
                             Model model){
        model.addAttribute("resumes", resumeService.getFindResumeById(resumeId));

        if (resumeBindingResult.hasErrors()){
            return "resumeAndVacancy/editResume";
        }

        resumeDto.setEducationInfo(List.of(educationInfoDto));
        resumeDto.setWorkExperienceInfo(List.of(workExperienceInfoDto));

        resumeService.getUpdateResume(resumeId, resumeDto);
        return "redirect:/users/profileApp";
    }

    @PostMapping("updateDate/{resumeId}")
    public String updateDate(@PathVariable Long resumeId){
        resumeService.getResumeUpdateDate(resumeId);
        return "redirect:/users/profileApp";
    }

}
