package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.model.EducationInfo;

import java.util.List;

public interface EducationInfoService {
    List<EducationInfo> getFindEducationInfoByResumeId(Long resumeId);
}
