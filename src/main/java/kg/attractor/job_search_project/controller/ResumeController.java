package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.ResumeService;
import kg.attractor.job_search_project.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("add")
    public HttpStatus addResume(@RequestBody ResumeDto resumeDto){
        resumeService.getCreateResume(resumeDto);
        return HttpStatus.OK;
    }

    @PutMapping("update/{resumeId}")
    public HttpStatus updateResume(@RequestBody ResumeDto resumeDto, @PathVariable Long resumeId){
        resumeService.getUpdateResume(resumeId, resumeDto);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{resumeId}")
    public HttpStatus  deleteResume(@PathVariable("resumeId") Long resumeId){
        resumeService.getDeleteResume(resumeId);
        return HttpStatus.OK;
    }

    @GetMapping("vacancy")
    public ResponseEntity<List<Resume>> vacancy(){
        //TODO logics
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public List<ResumeDto> getAllVacancyByCategory(@PathVariable String category) {
        return resumeService.getAllVacancyByCategory(category);
    }

    @GetMapping("userId/{userId}")
    public List<ResumeDto> getAllResumeByUserId(@PathVariable Long userId){
        return resumeService.getUserById(userId);
    }

    @GetMapping("applicant/{idvacancy}")
    public List<RespondedApplicantDto> responseVacancy(@PathVariable Long idvacancy){
        return resumeService.getresponseVacancy(idvacancy);
    }

}
