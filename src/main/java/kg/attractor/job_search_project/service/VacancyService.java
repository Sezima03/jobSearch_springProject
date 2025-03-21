package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.Vacancy;

import java.sql.Timestamp;
import java.util.List;

public interface VacancyService {

    Vacancy createdVacancy(Vacancy vacancy);

    Vacancy updateVacancy(Long id, Vacancy updateVacancy);

    boolean deleteVacancy(Long id);


    List<ResumeDto> getAllResume();

    List<VacancyDto> getVacancyByCategory(Long category_id);

    List<VacancyDto> getRespondedToVacancy(Long applicantId);

    List<VacancyDto> getVacancy();

    List<Vacancy> getSearchApplicant(String name);
}
