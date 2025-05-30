package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.VacancyDto;

import java.util.List;

public interface ResponsesApplicantService {
    void saveRespondedApplicant(long userId, long resumeId, long vacancyId, boolean confirmation);

    int countRespondedApplicantByUserId(long userId);

    List<RespondedApplicantDto> findAllVacancyById(long userId);

    List<RespondedApplicantDto> findAllRespondedApplicantByUserId(long userId);

    List<RespondedApplicantDto> getFundRespondedApplicantByVacancyId();

}
