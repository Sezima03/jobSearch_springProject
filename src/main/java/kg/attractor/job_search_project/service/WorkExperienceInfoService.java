package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.model.WorkExperienceInfo;

import java.util.List;

public interface WorkExperienceInfoService {
    List<WorkExperienceInfo> getFindWorkExperienceInfoByResumeID(Long resumeId);
}
