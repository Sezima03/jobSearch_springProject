package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.VacancyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vacancy")
@RequiredArgsConstructor
public class VacancyController {

    @PostMapping
    public HttpStatus createVacancy(@RequestBody VacancyDto vacancyDto) {
        //TODO logics
        return HttpStatus.CREATED;
    }

    @PutMapping("{vacancyId}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable("vacancyId") String id, @RequestBody VacancyDto vacancyDto) {
        //TODO logic for updating a vacancy
        return new ResponseEntity<>(vacancyDto, HttpStatus.OK);

    }

    @DeleteMapping("{vacancyId}")
    public ResponseEntity<VacancyDto> deleteVacancy(@PathVariable("vacancyId") String id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
