package kg.attractor.job_search_project.service;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.Vacancy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {


    void createdVacancy(VacancyDto vacancyDto);

    void getUpdateVacancy(VacancyDto vacancyDto, Long id);

    boolean deleteVacancy(Long id);

    List<VacancyDto> getVacancyByCategory(Long category_id);

    List<VacancyDto> getAllVacancy();

    List<VacancyDto> getVacancySortDesc();

    List<VacancyDto> getVacancySortAsc();

    List<RespondedApplicantDto>  getRespondedApplicantByVacancyId(Long vacancyId);

    List<VacancyDto> getAllActiveVacancies();

    VacancyDto getFindVacancyById(Long vacancyId);

    Page<VacancyDto> getAllVacancyByUserId(Long userId, int page, int size);

    void getUpdateVacancyDate(Long vacancyId);

}
