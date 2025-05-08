package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.model.WorkExperienceInfo;
import kg.attractor.job_search_project.repository.WorkExperienceInfoRepository;
import kg.attractor.job_search_project.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoImpl implements WorkExperienceInfoService {
    private final WorkExperienceInfoRepository workExperienceInfoRepository;

    @Override
    public List<WorkExperienceInfo> getFindWorkExperienceInfoByResumeID(Long resumeId) {
        return workExperienceInfoRepository.findWorkExperienceInfoByResumeId(resumeId);
    }
}
