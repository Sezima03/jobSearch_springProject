package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.EducationInfo;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.repository.EducationInfoRepository;
import kg.attractor.job_search_project.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationInfoImpl implements EducationInfoService {
    private final EducationInfoRepository educationInfoRepository;

    @Override
    public List<EducationInfo> getFindEducationInfoByResumeId(Long resumeId) {
        return educationInfoRepository.findEducationInfoByResumeId(resumeId);
    }

    @Override
    public void saveEducationInfo(Resume resume, List<EducationInfoDto> educationInfoDtos) {
        for (EducationInfoDto educationInfoDto : educationInfoDtos) {
            EducationInfo educationInfo = new EducationInfo();
            educationInfo.setId(educationInfoDto.getId());
            educationInfo.setResume(resume);
            educationInfo.setInstitution(educationInfoDto.getInstitution());
            educationInfo.setProgram(educationInfoDto.getProgram());
            educationInfo.setStartDate(educationInfoDto.getStartDate());
            educationInfo.setEndDate(educationInfoDto.getEndDate());
            educationInfo.setDegree(educationInfoDto.getDegree());

            educationInfoRepository.save(educationInfo);
            log.info("Education info for resume Id {}", resume.getId());
        }
    }

    @Override
    public void updateEducationInfo(Resume resume, EducationInfoDto eduDto) {
        EducationInfo educationInfo = educationInfoRepository.findById(eduDto.getId())
                .orElseThrow(() -> new JobSearchException("Education not found"));

        educationInfo.setResume(resume);
        educationInfo.setInstitution(eduDto.getInstitution());
        educationInfo.setProgram(eduDto.getProgram());
        educationInfo.setStartDate(eduDto.getStartDate());
        educationInfo.setEndDate(eduDto.getEndDate());
        educationInfo.setDegree(eduDto.getDegree());

        educationInfoRepository.save(educationInfo);
        log.info("Education info for resume Id {} updated successfully", resume.getId());
    }
}
