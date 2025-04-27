package kg.attractor.job_search_project.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.ResumeService;
import kg.attractor.job_search_project.service.UserService;
import kg.attractor.job_search_project.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final UserService userService;


    @GetMapping
    public String allVacancies(Model model) {
        List<VacancyDto> vacancyDtos = vacancyService.getVacancy();
        model.addAttribute("vacancies", vacancyDtos);
        return "list/allvacancy";
    }

    @GetMapping("allResume")
    public String allResume(Model model) {
        List<ResumeDto> resumeDtos = resumeService.getAllResume();
        model.addAttribute("resumes", resumeDtos);
        return "list/allresume";
    }

    @GetMapping("info/{id}")
    public String vacancyInfo(@PathVariable Long id, Model model) {
        VacancyDto vacancyDto = vacancyService.getFindVacancyById(id);
        model.addAttribute("vacancy", vacancyDto);
        return "list/vacancyInfo";
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
        if (bindingResult.hasErrors()) {
            return "resumeAndVacancy/createdVacancy";
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
}
