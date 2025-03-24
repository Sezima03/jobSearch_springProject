package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resume")
@RequiredArgsConstructor
public class ApplicantController {
    private final ResumeService resumeService;

    //TODO реализован
    @GetMapping
    public List<ResumeDto> resumes() {
        return resumeService.getAllResume();
    }
//TODO yes
    @PostMapping("add")
    public HttpStatus addResume(@RequestBody ResumeDto resumeDto){
        resumeService.getCreateResume(resumeDto);
        return HttpStatus.OK;
    }
    //TODO реализован
    @PutMapping("update/{resumeId}")
    public HttpStatus updateResume(@RequestBody Resume resume, @PathVariable Long resumeId){
        resumeService.getUpdateResume(resumeId, resume);
        return HttpStatus.OK;
    }
    //TODO yes
    @DeleteMapping("delete/{resumeId}")
    public HttpStatus  deleteResume(@PathVariable("resumeId") Long resumeId){
        resumeService.getDeleteResume(resumeId);
        return HttpStatus.OK;
    }

    //TODO посмотреть нужен ли этот метод
    @GetMapping("/category/{category}")
    public List<ResumeDto> getAllResumeByCategory(@PathVariable String category) {
        return resumeService.getAllResumeByCategory(category);
    }

    //TODO реализован
    @GetMapping("find/{resumeId}")
    public ResumeDto findResumeById(@PathVariable Long resumeId){
        return resumeService.getFindResumeById(resumeId);
    }
    @GetMapping("applicant/{idvacancy}")
    private List<RespondedApplicantDto> responsesVacancy(@PathVariable Long idvacancy){
        return resumeService.getresponseVacancy(idvacancy);
    }

//    @GetMapping("user/{userId}")
//    private List<ResumeDto> allResumesByUSerID(@PathVariable Long userId) {
//        return resumeServiceImpl.findAllResumeByUserId(userId);
//    }
}
