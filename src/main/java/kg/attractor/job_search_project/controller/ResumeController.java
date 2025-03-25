package kg.attractor.job_search_project.controller;

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
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping
    public List<ResumeDto> resumes() {
        return resumeService.getAllResume();
    }

    @PostMapping("add")
    public HttpStatus addResume(@RequestBody ResumeDto resumeDto){
        resumeService.getCreateResume(resumeDto);
        return HttpStatus.OK;
    }

    @PutMapping("update/{resumeId}")
    public HttpStatus updateResume(@RequestBody Resume resume, @PathVariable Long resumeId){
        resumeService.getUpdateResume(resumeId, resume);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{resumeId}")
    public HttpStatus  deleteResume(@PathVariable("resumeId") Long resumeId){
        resumeService.getDeleteResume(resumeId);
        return HttpStatus.OK;
    }

    @GetMapping("/category/{category}")
    public List<ResumeDto> getAllResumeByCategory(@PathVariable String category) {
        return resumeService.getAllResumeByCategory(category);
    }

    @GetMapping("find/{resumeId}")
    public ResumeDto findResumeById(@PathVariable Long resumeId){

        return resumeService.getFindResumeById(resumeId);
    }


    @GetMapping("applicant/{id}")
    private List<ResumeDto> allResumesByApplicantID(@PathVariable Long id) {
        return resumeService.getAllResumeByApplicantId(id);
    }
}
