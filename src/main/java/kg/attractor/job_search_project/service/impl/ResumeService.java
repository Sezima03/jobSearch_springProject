package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;

import java.util.List;

public interface ResumeService {
    Resume getCreateResume(Resume resume);

    Resume getUpdateResume(Resume resumeId);

    Resume getDeleteResume(String resumeId);

    List<Resume> getAllVacancyByCategory(String category);

    String getresponceVacancy(String resumeId);

    List<Vacancy> getSearchForAnEmployer(String name);
}
