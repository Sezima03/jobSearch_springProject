package kg.attractor.job_search_project.controller;

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

    @PostMapping
    public ResponseEntity<Resume>  createResume(@RequestBody Resume resume){
        Resume createR= resumeService.getCreateResume(resume);
        return ResponseEntity.status(HttpStatus.CREATED).body(createR);
    }

    @PutMapping("update/{resumeId}")
    public ResponseEntity<Resume>  updateResume(@PathVariable Long resumeId, @RequestBody Resume updateResume){
        Resume resume1 = resumeService.getUpdateResume(resumeId, updateResume);
        return ResponseEntity.status(HttpStatus.OK).body(resume1);
    }

    @DeleteMapping("delete/{resumeId}")
    public HttpStatus  deleteResume(@PathVariable("resumeId") Long resumeId){
        return HttpStatus.OK;
    }

    @GetMapping("vacancy")
    public ResponseEntity<List<Resume>> getAllResume(){
        //TODO logics
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public List<ResumeDto> getAllVacancyByCategory(@PathVariable String category) {
        return resumeService.getAllVacancyByCategory(category);
    }

    @PostMapping("response")
    public ResponseEntity<?> responseToVacancy(@RequestParam Long resumeId,  @RequestParam Long vacancyId){
         String response =resumeService.getresponceVacancy(resumeId,vacancyId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("searchemployer")
    public ResponseEntity<List<Vacancy>> searchForEmployer(@RequestParam String name){
        List<Vacancy> employer=resumeService.getSearchForAnEmployer(name);
        return ResponseEntity.status(HttpStatus.OK).body(employer);
    }
}
