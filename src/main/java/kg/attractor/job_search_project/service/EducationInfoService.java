package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.model.EducationInfo;
import kg.attractor.job_search_project.model.Resume;

import java.util.List;

public interface EducationInfoService {
    List<EducationInfo> getFindEducationInfoByResumeId(Long resumeId);

    void saveEducationInfo(Resume resume, List<EducationInfoDto> educationInfoDtos);

    void updateEducationInfo(Resume resume, List<EducationInfoDto> eduDto);
}
