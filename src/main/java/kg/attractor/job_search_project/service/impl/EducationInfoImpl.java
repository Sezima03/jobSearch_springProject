package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.model.EducationInfo;
import kg.attractor.job_search_project.repository.EducationInfoRepository;
import kg.attractor.job_search_project.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoImpl implements EducationInfoService {
    private final EducationInfoRepository educationInfoRepository;

    @Override
    public List<EducationInfo> getFindEducationInfoByResumeId(Long resumeId) {
        return educationInfoRepository.findEducationInfoByResumeId(resumeId);
    }
}
