package kg.attractor.job_search_project.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("created")
    public String createdVacancy(Model model) {
        model.addAttribute("vacancy", new VacancyDto());
        return "resumeAndVacancy/createdVacancy";
    }

    @PostMapping("created")
    public String createdVacancy(@ModelAttribute("vacancy") @Valid VacancyDto vacancyDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (!bindingResult.hasErrors()) {
            vacancyService.createdVacancy(vacancyDto);
            return "redirect:/";
        }

        model.addAttribute("vacancy", vacancyDto);
        return "resumeAndVacancy/createdVacancy";
    }

    @GetMapping("edit/{vacancyId}")
    public String updateVacancy(Model model,
                                @PathVariable Long vacancyId,
                                VacancyDto vacancyDto) {
        model.addAttribute("vacancy", vacancyService.getFindVacancyById(vacancyId));
        model.addAttribute("vacancyDto", vacancyDto);
        return "resumeAndVacancy/editVacancy";
    }

    @PostMapping("edit/{vacancyId}")
    public String updateVacancy(@PathVariable Long vacancyId,
                                @ModelAttribute("vacancy") @Valid VacancyDto vacancyDto,
                                BindingResult bindingResult,
                                Model model) {

        model.addAttribute("vacancy", vacancyService.getFindVacancyById(vacancyId));

        if (bindingResult.hasErrors()) {
            return "resumeAndVacancy/editVacancy";
        }
        vacancyService.getUpdateVacancy(vacancyDto, vacancyId);
        return "redirect:/";
    }


}
