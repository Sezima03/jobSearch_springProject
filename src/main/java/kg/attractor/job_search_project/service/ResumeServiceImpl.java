package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.impl.ResumeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Override
    public Resume getCreateResume(Resume resume) {
        //TODO логика создание резюме
        return resume;
    }

    @Override
    public Resume getUpdateResume(Resume resumeId) {
        //TODO логика для редактирования
        return resumeId;
    }

    @Override
    public Resume getDeleteResume(String resumeId) {
        //TODO логика для удаления резюме

        return null;
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
