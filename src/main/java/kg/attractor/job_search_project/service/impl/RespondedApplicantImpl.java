package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.RespondedApplicant;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.repository.RespondedApplicantRepository;
import kg.attractor.job_search_project.service.ResponsesApplicantService;
import kg.attractor.job_search_project.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondedApplicantImpl implements ResponsesApplicantService {
    private final RespondedApplicantRepository replyRepository;
    private final ResumeService resumeService;

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
                .map(respondedApplicant -> {
                    Resume resume = resumeService.getFindResumeByID(respondedApplicant.getResumeId());
                    return RespondedApplicantDto.builder()
                            .id(respondedApplicant.getId())
                            .resumeId(respondedApplicant.getResumeId())
                            .resumeName(resume.getName())
                            .vacancyId(respondedApplicant.getVacancyId())
                            .confirmation(respondedApplicant.isConfirmation())
                            .build();
                })
                .toList();
    }

    @Override
    public List<RespondedApplicantDto> getFundRespondedApplicantByVacancyId() {
        List<RespondedApplicant> find = replyRepository.findAllRespondedApplicant();
        return find.stream()
                .map(respondedApplicant -> RespondedApplicantDto.builder()
                        .id(respondedApplicant.getId())
                        .userId(respondedApplicant.getUserId())
                        .resumeId(respondedApplicant.getResumeId())
                        .vacancyId(respondedApplicant.getVacancyId())
                        .confirmation(respondedApplicant.isConfirmation())
                        .build()
                )
                .toList();
    }

}
