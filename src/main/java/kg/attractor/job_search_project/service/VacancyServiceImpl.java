package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.impl.VacancyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {


    public List<Vacancy> getAllResume;

    @Override
    public Vacancy createdVacancy(Vacancy vacancy) {
        //TODO логика для создании вакансии
        return vacancy;
    }

    @Override
    public Vacancy updateVacancy(String id, Vacancy vacancyDto) {
        //TODO there will be logic for updating the vacancy
        return vacancyDto;
    }

    @Override
    public void deleteVacancy(String id) {
        //TODO logic for deletion
    }

    @Override
    public List<Vacancy> getAllResume() {
        //TODO логика для возвращения всех вакансиц
        return null;
    }

    @Override
    public List<Vacancy> getVacancyByCategory(String category) {
        //TODO логика поиска резюме по категориям
        return List.of();
    }

    @Override
    public List<Vacancy> getRespondedToVacancy() {
        //TODO logic for job seekers who responded to the vacancy
        return List.of();
    }

    @Override
    public List<Vacancy> getSearchApplicant(String name) {
        //TODO логика для поиска соискателя
        return List.of();
    }
}
