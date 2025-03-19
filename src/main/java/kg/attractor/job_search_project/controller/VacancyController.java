package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.model.Vacancy;
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
    private VacancyServiceImpl vacancyServiceImp;

    @PostMapping
    public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy) {
        Vacancy createVacancy=vacancyServiceImp.createdVacancy(vacancy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createVacancy);
    }

    @PutMapping("update/{vacancyId}")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable("vacancyId") Long id, @RequestBody Vacancy vacancy) {
        Vacancy update=vacancyServiceImp.updateVacancy(id, vacancy);
        return ResponseEntity.status(HttpStatus.OK).body(update);

    }

    @DeleteMapping("delete/{vacancyId}")
    public HttpStatus deleteVacancy(@PathVariable("vacancyId") String id) {
        //TODO logic
        return HttpStatus.OK;
    }

    @GetMapping("searchresume")
    public ResponseEntity<Void> allResume() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("category")
    public ResponseEntity<List<Vacancy>> vacancyByCategory(@PathVariable("category") String category) {
        List<Vacancy> vacancyCategory=vacancyServiceImp.getVacancyByCategory(category);
        return ResponseEntity.ok(vacancyCategory);
    }

    @GetMapping("responded")
    public ResponseEntity<List<Vacancy>> searchRespondedToVacancy(){
        List<Vacancy> search = vacancyServiceImp.getRespondedToVacancy();
        return ResponseEntity.ok(search);
    }

    @GetMapping("applicant")
    public ResponseEntity <List<Vacancy>> searchForApplicant(@RequestParam String name){
        List<Vacancy> search = vacancyServiceImp.getSearchApplicant(name);
        return ResponseEntity.ok(search);
    }

}
