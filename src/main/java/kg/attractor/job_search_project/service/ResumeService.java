package kg.attractor.job_search_project.service;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.model.Resume;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ResumeService {


    void getCreateResume(ResumeDto resume);

    void getUpdateResume(Long resumeId, ResumeDto updateResume);

    boolean getDeleteResume(Long resumeId);

    List<ResumeDto> getAllResumeByCategory(String category);

    ResumeDto getFindResumeById(Long userId);


    List<ResumeDto> getAllResume();


    List<ResumeDto> getAllResumeByApplicantId(Long applicantId);

    Page<ResumeDto> getAllResumeByUserId(Long userId, int page, int size);

    List<ResumeDto> allResumeByUserId(Long userId);

    void getResumeUpdateDate(Long resumeId);

    Resume getFindResumeByID(Long resumeId);
}