package kg.attractor.job_search_project.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.*;
import kg.attractor.job_search_project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ResponsesApplicantService responsesApplicantService;


    @GetMapping
    public String allVacancies(Model model) {
        List<VacancyDto> vacancyDtos = vacancyService.getAllVacancy();
        model.addAttribute("vacancies", vacancyDtos);
        return "vacancy/vacancies";
    }

    @GetMapping("allVacanciesForAuth")
    public String allVacanciesForAuth(Model model) {
        List<VacancyDto> vacancyDtoList = vacancyService.getAllVacancy();
        model.addAttribute("vacancies", vacancyDtoList);
        return "vacancy/vacancyForAuth";
    }

    @GetMapping("sortDesc")
    public String sortVacancy(Model model) {
        List<VacancyDto> vacancySort = vacancyService.getVacancySortDesc();
        model.addAttribute("vacancies", vacancySort);
        return "vacancy/vacancies";
    }

    @GetMapping("sortAsc")
    public String sortVacancyAsc(Model model) {
        List<VacancyDto> vacancySortAsc = vacancyService.getVacancySortAsc();
        model.addAttribute("vacancies", vacancySortAsc);
        return "vacancy/vacancies";
    }

    @GetMapping("infoForUnauthorized/{id}")
    public String infoForUnauthorized( @PathVariable Long id, Model model) {
        VacancyDto vacancyDto = vacancyService.getFindVacancyById(id);
        Category category = categoryService.categoryName(vacancyDto.getCategoryId());

        model.addAttribute("vacancyDto", vacancyDto);
        model.addAttribute("category", category);

        return "vacancy/vacancyInfo";
    }

    @GetMapping("info/{id}")
    public String vacancyInfo(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByUsername(name);
        List<ResumeDto> resumeDto =resumeService.allResumeByUserId(user.getId());
        VacancyDto vacancyDto = vacancyService.getFindVacancyById(id);
        Category category = categoryService.categoryName(vacancyDto.getCategoryId());
        model.addAttribute("vacancyDto", vacancyDto);
        model.addAttribute("category", category);
        model.addAttribute("resumes", resumeDto);
        return "vacancy/vacancyInfoForAuth";
    }

    @GetMapping("created")
    public String createdVacancy(Model model, VacancyDto vacancyDto) {
        model.addAttribute("vacancy", vacancyDto);
        return "vacancy/created";
    }

    @PostMapping("created")
    public String createdVacancy(@ModelAttribute("vacancy") @Valid VacancyDto vacancyDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vacancy/created";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);

        vacancyDto.setAuthorId(user.getId());
        vacancyService.createdVacancy(vacancyDto);

        return "redirect:/users/profileEmp";
    }

    @GetMapping("edit/{vacancyId}")
    public String updateVacancy(Model model,
                                @PathVariable Long vacancyId) {
        VacancyDto vacancy = vacancyService.getFindVacancyById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        return "vacancy/edit";
    }

    @PostMapping("edit/{vacancyId}")
    public String updateVacancy(@PathVariable Long vacancyId,
                                @ModelAttribute("vacancy") @Valid VacancyDto vacancyDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vacancy/edit";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);
        vacancyDto.setAuthorId(user.getId());
        vacancyService.getUpdateVacancy(vacancyDto, vacancyId);
        return "redirect:/users/profileEmp";
    }


    @PostMapping("updateDate/{vacancyId}")
    public String updateDate(@PathVariable Long vacancyId){
        vacancyService.getUpdateVacancyDate(vacancyId);
        return "redirect:/users/profileEmp";
    }

    @PostMapping("applyForVacancy/{id}")
    public String applyForVacancy(@PathVariable Long id,
                                  @RequestParam Long resumeId,
                                  Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        responsesApplicantService.saveRespondedApplicant(user.getId(), resumeId, id, false);
        return "redirect:/info/" + id;
    }

    @GetMapping("responseToVacancy")
    public String responseToVacancy(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);

        List<RespondedApplicantDto> respondedApplicantDto = responsesApplicantService.findAllRespondedApplicantByUserId(user.getId());

        model.addAttribute("responses", respondedApplicantDto);
        return "vacancy/response";
    }

}
