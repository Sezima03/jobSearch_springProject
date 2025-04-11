package kg.attractor.job_search_project.controller;

import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;


    @GetMapping("allVacancy")
    public String allVacancies(Model model) {
        List<VacancyDto> vacancyDtos = vacancyService.getVacancy();
        model.addAttribute("vacancies", vacancyDtos);
        return "list/allvacancy";
    }

}
