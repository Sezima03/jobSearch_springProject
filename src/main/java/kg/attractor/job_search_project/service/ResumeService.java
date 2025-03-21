package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;

import java.util.List;

public interface ResumeService {

    void getCreateResume(ResumeDto resumeDto);

    void getUpdateResume(Long resumeId, ResumeDto updateResume);

    boolean getDeleteResume(Long resumeId);

    List<ResumeDto> getAllVacancyByCategory(String category);

    List<ResumeDto> getUserById(Long userId);

    List<RespondedApplicantDto> getresponseVacancy(Long vacancyId);

    List<Vacancy> getSearchForAnEmployer(String name);
}