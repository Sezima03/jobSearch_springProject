package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.model.Vacancy;

import java.sql.Timestamp;
import java.util.List;

public interface VacancyService {
    Vacancy createdVacancy(Vacancy vacancy);

    Vacancy updateVacancy(Long id, Vacancy updateVacancy);

    boolean deleteVacancy(Long id);

    List<Vacancy> getAllResume();

    List<Vacancy> getVacancyByCategory(String category);

    List<Vacancy> getRespondedToVacancy();

    List<Vacancy> getSearchApplicant(String name);
}
