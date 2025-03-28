package kg.attractor.job_search_project.controller;
import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("add")
    public HttpStatus addVacancy(@RequestBody @Valid VacancyDto vacancyDto){
        vacancyService.createdVacancy(vacancyDto);
        return HttpStatus.OK;
    }

    @PutMapping("update/{vacancyId}")
    public HttpStatus updateVacancy(@RequestBody @Valid VacancyDto vacancyDto, @PathVariable Long vacancyId){
        vacancyService.getUpdateVacancy(vacancyDto, vacancyId);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{vacancyId}")
    public HttpStatus deleteVacancy(@PathVariable("vacancyId") Long id) {
        vacancyService.deleteVacancy(id);
        return HttpStatus.OK;
    }


    @GetMapping("category/{id}")
    public List<VacancyDto> getVacancyByCategoryId(@PathVariable("id") Long id) {
        return vacancyService.getVacancyByCategory(id);
    }


    @GetMapping("responded/{vacancyId}")
    public List<RespondedApplicantDto> findRespondedApplicantByVacancyId(@PathVariable("vacancyId") Long vacancyId) {
        return vacancyService.getRespondedApplicantByVacancyId(vacancyId);
    }

    @GetMapping("active")
    public List<VacancyDto> getActiveVacancies() {
        return vacancyService.getAllActiveVacancies();
    }
}
