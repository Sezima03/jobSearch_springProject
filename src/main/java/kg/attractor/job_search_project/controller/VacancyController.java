package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.VacancyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vacancy")
@RequiredArgsConstructor
public class VacancyController {

    @PostMapping
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody VacancyDto vacancyDto) {
        //TODO logics
        return new ResponseEntity<>(vacancyDto, HttpStatus.OK);
    }

}
