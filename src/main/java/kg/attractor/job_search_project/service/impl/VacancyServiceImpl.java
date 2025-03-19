package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.VacancyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Override
    public Vacancy createdVacancy(Vacancy vacancy) {
        //TODO входные данные: name, description, category_id, salary, exp_from, exp_to, isActive, author_id, created_date, update_date
        //TODO логика для создании вакансии и сохранение
        //TODO Возвращаем новую ваканисию
        return vacancy;
    }

    @Override
    public Vacancy updateVacancy(Long id, Vacancy updateVacancy) {
        //TODO логика для редактирования вакансии пользователя
        //TODO входные данные: name, description, category_id, salary, exp_from, exp_to, isActive, author_id, created_date, update_date
        //TODO по id ещем соответствующую вакансию и обновляем данные вакансии
        //TODO возращаем обновленную вакансию
        return updateVacancy;
    }

    @Override
    public boolean deleteVacancy(Long id) {
        //TODO Удаляем вакансию по id
        //TODO возвращаем true если вакансия удалена
        return true;
    }

    @Override
    public List<Vacancy> getAllResume() {
        //TODO логика для возвращения всех резюме
        //TODO Получаем резюме из базы и можно вовращать их в виде списка

        return new ArrayList<>(); //Пока что возвращаю пустой список
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
