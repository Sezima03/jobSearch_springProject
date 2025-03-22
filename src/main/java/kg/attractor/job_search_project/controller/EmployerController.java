package kg.attractor.job_search_project.controller;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancy")
@RequiredArgsConstructor
public class EmployerController {
    private final VacancyServiceImpl vacancyServiceImpl;

    @GetMapping
    public List<VacancyDto> vacancy(){

        return vacancyServiceImpl.getVacancy();
    }

    @PostMapping("add")
    public HttpStatus addVacancy(@RequestBody VacancyDto vacancyDto){
        vacancyServiceImpl.createdVacancy(vacancyDto);
        return HttpStatus.OK;
    }

    @PutMapping("update/{vacancyId}")
    public HttpStatus updateVacancy(@RequestBody VacancyDto vacancyDto, @PathVariable Long vacancyId){
        vacancyServiceImpl.updateVacancy(vacancyDto, vacancyId);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{vacancyId}")
    public HttpStatus deleteVacancy(@PathVariable("vacancyId") Long id) {
        vacancyServiceImpl.deleteVacancy(id);
        return HttpStatus.OK;
    }

    @GetMapping("category/{id}")
    public List<VacancyDto> getVacancyByCategoryId(@PathVariable("id") Long id) {
        return vacancyServiceImpl.getVacancyByCategory(id);
    }
}
