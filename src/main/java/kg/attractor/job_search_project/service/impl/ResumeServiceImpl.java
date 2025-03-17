package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.ResumeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Override
    public Resume getCreateResume(Resume resume) {
        //TODO входные данные applicant_id, name, category_id, salary, isActive, created_date, update_date
        //TODO логика создание резюме
        //TODO возвращаем новое резюме
        return resume;
    }

    @Override
    public Resume getUpdateResume(Resume resumeId) {
        //TODO входные данные applicant_id, name, category_id, salary, isActive, created_date, update_date
        //TODO По id обновляем резбме
        //TODO возвращаем обновленное резюме
        return resumeId;
    }


    @Override
    public boolean getDeleteResume(String resumeId) {
        //TODO Удаляем резюме по id
        //TODO возвращаем true если вакансия удалена
        return true;
    }

    @Override
    public List<Resume> getAllVacancyByCategory(String category) {
        //TODO поиск вакансий по категориям
        return List.of();
    }

    @Override
    public String getresponceVacancy(String resumeId) {
        return resumeId;
    }

    @Override
    public List<Vacancy> getSearchForAnEmployer(String name) {
        //TODO логика поиска работодателя

        return List.of();
    }
}
