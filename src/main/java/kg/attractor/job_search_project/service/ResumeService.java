package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;


import java.util.List;

public interface ResumeService {


    void getCreateResume(ResumeDto resumeDto);

    void getUpdateResume(Long resumeId, ResumeDto updateResume);

    boolean getDeleteResume(Long resumeId);

    List<ResumeDto> getAllResumeByCategory(String category);

    List<ResumeDto> getUserById(Long userId);


    List<RespondedApplicantDto> getresponseVacancy(Long vacancyId);

    List<ResumeDto> getAllResume();
}