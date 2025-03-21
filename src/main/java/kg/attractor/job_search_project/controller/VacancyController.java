package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.VacancyService;
import kg.attractor.job_search_project.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public List<VacancyDto> vacancy(){
        return vacancyService.getVacancy();
    }

    @PostMapping("create")
    public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy) {
        Vacancy createVacancy=vacancyService.createdVacancy(vacancy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createVacancy);
    }

    @PutMapping("update/{vacancyId}")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable("vacancyId") Long id, @RequestBody Vacancy vacancy) {
        Vacancy update=vacancyService.updateVacancy(id, vacancy);
        return ResponseEntity.status(HttpStatus.OK).body(update);

    }

    @DeleteMapping("delete/{vacancyId}")
    public HttpStatus deleteVacancy(@PathVariable("vacancyId") String id) {
        //TODO logic
        return HttpStatus.OK;
    }

    @GetMapping("resume")
    public List<ResumeDto> getResume() {
        return vacancyService.getAllResume();
    }

    @GetMapping("category/{id}")
    public List<VacancyDto> getVacancyByCategory(@PathVariable("id") Long id) {
        return vacancyService.getVacancyByCategory(id);
    }

    @GetMapping("responded/{responded}")
    public List<VacancyDto>  vacancyByResponded(@PathVariable("responded") Long applicantId) {
        return vacancyService.getRespondedToVacancy(applicantId);
    }

    @GetMapping("applicant")
    public ResponseEntity <List<Vacancy>> searchForApplicant(@RequestParam String name){
        List<Vacancy> search = vacancyService.getSearchApplicant(name);
        return ResponseEntity.ok(search);
    }

}

