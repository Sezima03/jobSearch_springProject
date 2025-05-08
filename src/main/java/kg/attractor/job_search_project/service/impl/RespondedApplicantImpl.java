package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.model.RespondedApplicant;
import kg.attractor.job_search_project.repository.RespondedApplicantRepository;
import kg.attractor.job_search_project.service.ResponsesApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondedApplicantImpl implements ResponsesApplicantService {
    private final RespondedApplicantRepository replyRepository;

    @Override
    public void saveRespondedApplicant(long userId, long resumeId, long vacancyId, boolean confirmation) {
        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setUserId(userId);
        respondedApplicant.setResumeId(resumeId);
        respondedApplicant.setVacancyId(vacancyId);
        respondedApplicant.setConfirmation(false);
        replyRepository.save(respondedApplicant);
    }

    @Override
    public int countRespondedApplicantByUserId(long userId) {
        return replyRepository.countRespondedApplicantByUserId(userId);
    }
}
