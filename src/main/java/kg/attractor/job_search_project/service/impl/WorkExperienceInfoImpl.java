package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.WorkExperienceInfo;
import kg.attractor.job_search_project.repository.WorkExperienceInfoRepository;
import kg.attractor.job_search_project.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkExperienceInfoImpl implements WorkExperienceInfoService {
    private final WorkExperienceInfoRepository workExperienceInfoRepository;

    @Override
    public List<WorkExperienceInfo> getFindWorkExperienceInfoByResumeID(Long resumeId) {
        return workExperienceInfoRepository.findWorkExperienceInfoByResumeId(resumeId);
    }

    @Override
    public void saveWorkExperienceInfo(Resume resume, List<WorkExperienceInfoDto> workExperienceInfoDtos) {
        for (WorkExperienceInfoDto workExperienceInfoDto : workExperienceInfoDtos) {
            WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();
            workExperienceInfo.setResume(resume);
            workExperienceInfo.setYear(workExperienceInfoDto.getYear());
            workExperienceInfo.setCompanyName(workExperienceInfoDto.getCompanyName());
            workExperienceInfo.setPosition(workExperienceInfoDto.getPosition());
            workExperienceInfo.setResponsibilities(workExperienceInfoDto.getResponsibility());

            workExperienceInfoRepository.save(workExperienceInfo);
            log.info("WorkExperience info for resume Id {}", resume.getId());
        }
    }

    @Override
    public void updateWorkExperienceInfo(Resume resume, WorkExperienceInfoDto wei) {
        WorkExperienceInfo workExperienceInfo = workExperienceInfoRepository.findById(wei.getId())
                .orElseThrow(() -> new JobSearchException("WorkExperience info not found"));

        workExperienceInfo.setResume(resume);
        workExperienceInfo.setYear(wei.getYear());
        workExperienceInfo.setCompanyName(wei.getCompanyName());
        workExperienceInfo.setPosition(wei.getPosition());
        workExperienceInfo.setResponsibilities(wei.getResponsibility());

        workExperienceInfoRepository.save(workExperienceInfo);
        log.info("WorkExperience info for resume Id {} updated successfully", resume.getId());
    }
}
