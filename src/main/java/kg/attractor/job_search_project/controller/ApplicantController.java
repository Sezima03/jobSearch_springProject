package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resume")
@RequiredArgsConstructor
public class ApplicantController {
    private final ResumeServiceImpl resumeServiceImpl;

    @GetMapping
    public List<ResumeDto> getResumes() {
        return resumeServiceImpl.getAllResume();
    }

    @PostMapping("add")
    public HttpStatus addResume(@RequestBody ResumeDto resumeDto){
        resumeServiceImpl.getCreateResume(resumeDto);
        return HttpStatus.OK;
    }

    @PutMapping("update/{resumeId}")
    public HttpStatus updateResume(@RequestBody ResumeDto resumeDto, @PathVariable Long resumeId){
        resumeServiceImpl.getUpdateResume(resumeId, resumeDto);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{resumeId}")
    public HttpStatus  deleteResume(@PathVariable("resumeId") Long resumeId){
        resumeServiceImpl.getDeleteResume(resumeId);
        return HttpStatus.OK;
    }

    @GetMapping("/category/{category}")
    public List<ResumeDto> getAllResumeByCategory(@PathVariable String category) {
        return resumeServiceImpl.getAllResumeByCategory(category);
    }
    @GetMapping("userId/{userId}")
    public List<ResumeDto> getAllResumeByUserId(@PathVariable Long userId){
        return resumeServiceImpl.getUserById(userId);
    }
    @GetMapping("applicant/{idvacancy}")
    private List<RespondedApplicantDto> responsesVacancy(@PathVariable Long idvacancy){
        return resumeServiceImpl.getresponseVacancy(idvacancy);
    }
}
