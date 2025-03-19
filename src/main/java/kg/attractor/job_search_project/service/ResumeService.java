package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;

import java.util.List;

public interface ResumeService {
    Resume getCreateResume(Resume resume);


    Resume getUpdateResume(Long resumeId, Resume updateResume);

    boolean getDeleteResume(Long resumeId);

    List<Resume> getAllVacancyByCategory(String category);


    String getresponceVacancy(Long resumeId, Long vacancyId);

    List<Vacancy> getSearchForAnEmployer(String name);
}
