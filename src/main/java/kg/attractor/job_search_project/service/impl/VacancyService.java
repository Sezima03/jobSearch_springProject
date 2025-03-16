package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.model.Vacancy;

import java.util.List;

public interface VacancyService {

    Vacancy createdVacancy(Vacancy vacancy);

    Vacancy updateVacancy(String id, Vacancy vacancyDto);

    void deleteVacancy(String id);

    List<Vacancy> getAllResume();

    List<Vacancy> getVacancyByCategory(String category);

    List<Vacancy> getRespondedToVacancy();

    List<Vacancy> getSearchApplicant(String name);
}
