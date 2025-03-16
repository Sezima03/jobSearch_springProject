package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resume")
@RequiredArgsConstructor
public class ResumeController {
    private ResumeServiceImpl resumeServiceImpl;

    @PostMapping
    public ResponseEntity<Resume>  createResume(@RequestBody Resume resume){
        Resume createR= resumeServiceImpl.getCreateResume(resume);
        return ResponseEntity.status(HttpStatus.CREATED).body(createR);
    }

    @PutMapping("update/{resumeId}")
    public ResponseEntity<Resume>  updateResume(@RequestBody Resume resume){
        Resume resume1 = resumeServiceImpl.getUpdateResume(resume);
        return ResponseEntity.status(HttpStatus.OK).body(resume1);
    }

    @DeleteMapping("delete/{resumeId}")
    public ResponseEntity<Resume>  deleteResume(@PathVariable("resumeId") String resumeId){
        Resume resume = resumeServiceImpl.getDeleteResume(resumeId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resume);
    }

    @GetMapping("vacancy")
    public ResponseEntity<List<Resume>> getAllResume(){
        //TODO logics
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("category")
    public ResponseEntity<List<Resume>> getAllVacancyByCategory(String category){
        List<Resume> byCategory= resumeServiceImpl.getAllVacancyByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(byCategory);
    }

    @PostMapping("response")
    public ResponseEntity<?> responseToVacancy(@RequestBody Resume resumeId){
         resumeServiceImpl.getresponceVacancy(String.valueOf(resumeId));
        return ResponseEntity.ok(resumeId);
    }

    @GetMapping("searchemployer")
    public ResponseEntity<List<Vacancy>> searchForEmployer(@RequestParam String name){
        List<Vacancy> employer=resumeServiceImpl.getSearchForAnEmployer(name);
        return ResponseEntity.status(HttpStatus.OK).body(employer);
    }
}
