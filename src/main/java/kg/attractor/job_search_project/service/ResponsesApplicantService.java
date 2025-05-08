package kg.attractor.job_search_project.service;

public interface ResponsesApplicantService {
    void saveRespondedApplicant(long userId, long resumeId, long vacancyId, boolean confirmation);

    int countRespondedApplicantByUserId(long userId);
}
