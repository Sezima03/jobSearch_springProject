package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.WorkExperienceInfo;

import java.util.List;

public interface WorkExperienceInfoService {
    List<WorkExperienceInfo> getFindWorkExperienceInfoByResumeID(Long resumeId);

    void saveWorkExperienceInfo(Resume resume, List<WorkExperienceInfoDto> workExperienceInfoDtos);

    void updateWorkExperienceInfo(Resume resume, WorkExperienceInfoDto wei);
}
