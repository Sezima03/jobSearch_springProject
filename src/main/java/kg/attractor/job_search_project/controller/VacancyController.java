package kg.attractor.job_search_project.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.Category;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("info/{id}")
    public String vacancyInfo(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByUsername(name);
        List<ResumeDto> resumeDto =resumeService.getAllResumeByUserId(user.getId());
        VacancyDto vacancyDto = vacancyService.getFindVacancyById(id);
        Category category = categoryService.categoryName(vacancyDto.getCategoryId());
        model.addAttribute("vacancy", vacancyDto);
        model.addAttribute("category", category);
        model.addAttribute("resumes", resumeDto);
        return "vacancy/vacancyInfo";
    }

    @GetMapping("created")
    public String createdVacancy(Model model) {
        model.addAttribute("vacancy", new Vacancy());
        return "vacancy/created";
    }

    @PostMapping("created")
    public String createdVacancy(@ModelAttribute("vacancy") @Valid VacancyDto vacancyDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "vacancy/created";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);
        vacancyDto.setAuthorId(user.getId());
        model.addAttribute("vacancy", vacancyDto);
        vacancyService.createdVacancy(vacancyDto);
        return "redirect:/users/profileEmp";
    }

    @GetMapping("edit/{vacancyId}")
    public String updateVacancy(Model model,
                                @PathVariable Long vacancyId,
                                VacancyDto vacancyDto) {
        model.addAttribute("vacancy", vacancyService.getFindVacancyById(vacancyId));
        model.addAttribute("vacancyDto", vacancyDto);
        return "vacancy/edit";
    }

    @PostMapping("edit/{vacancyId}")
    public String updateVacancy(@PathVariable Long vacancyId,
                                @ModelAttribute("vacancy") @Valid VacancyDto vacancyDto,
                                BindingResult bindingResult,
                                Model model) {
        model.addAttribute("vacancy", vacancyService.getFindVacancyById(vacancyId));
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

    @GetMapping("responded")
    public String vacancyRespond(Model model) {
        List<VacancyDto> vacancyDto = vacancyService.getVacancyByResponses();
        model.addAttribute("vacancies", vacancyDto);
        return "list/responseCountToVacancy";
    }


    @GetMapping("update/{id}")
    public String updateEmpVacancy(@PathVariable Long id, Model model) {
        VacancyDto vacancy = vacancyService.getFindVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        return "temp/updateEmpVacancy";
    }

    @PostMapping("update/{id}")
    public String updateEmpVacancy(@PathVariable Long id,
                                   @ModelAttribute("vacancy") @Valid VacancyDto vacancyDto,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancy", vacancyDto);
            return "temp/updateEmpVacancy";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);
        vacancyDto.setAuthorId(user.getId());
        vacancyService.getUpdateVacancy(vacancyDto, id);
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

    @GetMapping("/responses")
    public String responsesToVacancy(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = vacancyService.getFindUserByName(username);

        List<RespondedApplicantDto> respondedApplicantDto = vacancyService.getFindAllResponseApplicantsByUserId(user.getId());
        model.addAttribute("respondedApplicant", respondedApplicantDto);
        return "vacancy/responsesToVacancy";
    }

}
