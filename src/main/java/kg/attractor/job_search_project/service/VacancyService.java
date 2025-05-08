package kg.attractor.job_search_project.service;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;

import java.util.List;

public interface VacancyService {


    void createdVacancy(VacancyDto vacancyDto);

    void getUpdateVacancy(VacancyDto vacancyDto, Long id);

    boolean deleteVacancy(Long id);

    List<VacancyDto> getVacancyByCategory(Long category_id);

    List<VacancyDto> getAllVacancy();

    List<VacancyDto> getVacancySortDesc();

    List<VacancyDto> getVacancySortAsc();

    List<VacancyDto> getVacancyByResponses();

    List<RespondedApplicantDto>  getRespondedApplicantByVacancyId(Long vacancyId);

    List<RespondedApplicantDto> getFindAllResponseApplicantsByUserId(Long userId);

    List<VacancyDto> getAllActiveVacancies();

    User getFindUserByName(String name);

    VacancyDto getFindVacancyById(Long vacancyId);

    List<VacancyDto> getAllVacancyByUserId(Long userId);

    void getUpdateVacancyDate(Long vacancyId);

}
