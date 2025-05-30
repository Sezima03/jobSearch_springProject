package kg.attractor.job_search_project.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import kg.attractor.job_search_project.model.EducationInfo;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.WorkExperienceInfo;
import kg.attractor.job_search_project.service.EducationInfoService;
import kg.attractor.job_search_project.service.ResumeService;
import kg.attractor.job_search_project.service.UserService;
import kg.attractor.job_search_project.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ResumeController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final EducationInfoService educationInfoService;
    private final WorkExperienceInfoService workExperienceInfoService;

    @GetMapping("resumes")
    public String allResume(Model model){
        List<ResumeDto> resumeDtos =resumeService.getAllResume();
        model.addAttribute("resumes", resumeDtos);
        return "resume/resumes";
    }

    @GetMapping("info/{id}")
    public String info(@PathVariable Long id, Model model){
        Resume resume = resumeService.getFindResumeByID(id);
        List<EducationInfo> educationInfo = educationInfoService.getFindEducationInfoByResumeId(id);
        List<WorkExperienceInfo> workExperienceInfo = workExperienceInfoService.getFindWorkExperienceInfoByResumeID(id);
        model.addAttribute("resume", resume);
        model.addAttribute("eduInfos", educationInfo);
        model.addAttribute("weis", workExperienceInfo);
        return "resume/info";
    }


    @GetMapping("create")
    public String createdResume(Model model, ResumeDto resumeDto,
                                EducationInfoDto educationInfoDto,
                                WorkExperienceInfoDto workExperienceInfoDto){
        model.addAttribute("eduIndex", 0);
        model.addAttribute("expIndex", 0);
        model.addAttribute("resumeDto", resumeDto);
        model.addAttribute("educationInfoDto", educationInfoDto);
        model.addAttribute("workExperienceInfoDto", workExperienceInfoDto);
        return "resume/createResume";
    }

    @PostMapping("create")
    public String createdResume(@ModelAttribute("resumeDto") @Valid ResumeDto resumeDto,
                                BindingResult bindingResultResume, Model model) {

        if (bindingResultResume.hasErrors()) {
            model.addAttribute("eduIndex", resumeDto.getEducationInfo() != null ? resumeDto.getEducationInfo().size() : 0);
            model.addAttribute("expIndex", resumeDto.getWorkExperienceInfo() != null ? resumeDto.getWorkExperienceInfo().size() : 0);
            return "resume/createResume";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByUsername(username);

        resumeDto.setApplicantId(user.getId());

        resumeService.getCreateResume(resumeDto);

        return "redirect:/users/profileApplicant";
    }


    @GetMapping("editResume/{resumeId}")
    public String editResume(Model model,
                             @PathVariable Long resumeId,
                             EducationInfoDto educationInfoDto,
                             WorkExperienceInfoDto workExperienceInfoDto){
        ResumeDto resumeDto = resumeService.getFindResumeById(resumeId);
        model.addAttribute("eduIndex", 0);
        model.addAttribute("expIndex", 0);
        model.addAttribute("resumes", resumeService.getFindResumeById(resumeId));
        model.addAttribute("resumeDto", resumeDto);
        model.addAttribute("educationfoDto", educationInfoDto);
        model.addAttribute("work", workExperienceInfoDto);
        return "resume/editResume";
    }

    @PostMapping("editResume/{resumeId}")
    public String editResume(@PathVariable Long resumeId,
                             @ModelAttribute("resumeDto") @Valid ResumeDto resumeDto,
                             BindingResult resumeBindingResult,
                             Model model
    ){
        model.addAttribute("resumes", resumeService.getFindResumeById(resumeId));

        if (resumeBindingResult.hasErrors()){
            model.addAttribute("eduIndex", resumeDto.getEducationInfo() != null ? resumeDto.getEducationInfo().size() : 0);
            model.addAttribute("expIndex", resumeDto.getWorkExperienceInfo() != null ? resumeDto.getWorkExperienceInfo().size() : 0);
            return "resume/editResume";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByUsername(username);
        resumeDto.setApplicantId(user.getId());

        resumeService.getUpdateResume(resumeId, resumeDto);
        log.info("Updated Resume with id {}",resumeId);
        return "redirect:/users/profileApplicant";
    }

    @PostMapping("updateDate/{resumeId}")
    public String updateDate(@PathVariable Long resumeId){
        resumeService.getResumeUpdateDate(resumeId);
        return "redirect:/users/profileApplicant";
    }
}