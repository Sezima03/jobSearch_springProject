package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.model.RespondedApplicant;
import kg.attractor.job_search_project.repository.RespondedApplicantRepository;
import kg.attractor.job_search_project.service.ResponsesApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<RespondedApplicantDto> findAllVacancyById(long userId) {
        List<RespondedApplicant> respondedApplicants = replyRepository.findAllByVacancyId(userId);
        return respondedApplicants.stream()
                .map(respondedApplicant -> RespondedApplicantDto.builder()
                        .id(respondedApplicant.getId())
                        .resumeId(respondedApplicant.getResumeId())
                        .vacancyId(respondedApplicant.getVacancyId())
                        .confirmation(respondedApplicant.isConfirmation())
                        .build())
                .toList();
    }

    @Override
    public List<RespondedApplicantDto> findAllRespondedApplicantByUserId(long userId) {
        List<RespondedApplicant> respondedApplicants = replyRepository.findAllRespondedApplicantByUserId(userId);
        return respondedApplicants.stream()
                .map(respondedApplicant -> RespondedApplicantDto.builder()
                        .id(respondedApplicant.getId())
                        .resumeId(respondedApplicant.getResumeId())
                        .vacancyId(respondedApplicant.getVacancyId())
                        .confirmation(respondedApplicant.isConfirmation())
                        .build())
                .toList();
    }
}
