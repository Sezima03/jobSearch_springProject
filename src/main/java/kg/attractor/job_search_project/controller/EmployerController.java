package kg.attractor.job_search_project.controller;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.service.VacancyService;
import kg.attractor.job_search_project.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancy")
@RequiredArgsConstructor
public class EmployerController {
    private final VacancyService vacancyService;

    //TODO работает
    @GetMapping
    public List<VacancyDto> vacancy(){
        return vacancyService.getVacancy();
    }
//TODO работвет
    @PostMapping("add")
    public HttpStatus addVacancy(@RequestBody VacancyDto vacancyDto){
        vacancyService.createdVacancy(vacancyDto);
        return HttpStatus.OK;
    }
//TODO yes
    @PutMapping("update/{vacancyId}")
    public HttpStatus updateVacancy(@RequestBody VacancyDto vacancyDto, @PathVariable Long vacancyId){
        vacancyService.updateVacancy(vacancyDto, vacancyId);
        return HttpStatus.OK;
    }
//TODO yes
    @DeleteMapping("delete/{vacancyId}")
    public HttpStatus deleteVacancy(@PathVariable("vacancyId") Long id) {
        vacancyService.deleteVacancy(id);
        return HttpStatus.OK;
    }

    @GetMapping("category/{id}")
    public List<VacancyDto> getVacancyByCategoryId(@PathVariable("id") Long id) {
        return vacancyService.getVacancyByCategory(id);
    }
}
