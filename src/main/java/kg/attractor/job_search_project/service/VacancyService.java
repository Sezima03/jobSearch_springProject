package kg.attractor.job_search_project.service;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.VacancyDto;

import java.util.List;

public interface VacancyService {


    void createdVacancy(VacancyDto vacancyDto);

    void getUpdateVacancy(VacancyDto vacancyDto, Long id);

    boolean deleteVacancy(Long id);

    List<VacancyDto> getVacancyByCategory(Long category_id);

    List<VacancyDto> getVacancy();

    List<RespondedApplicantDto>  getRespondedApplicantByVacancyId(Long vacancyId);

    List<VacancyDto> getAllActiveVacancies();
}
