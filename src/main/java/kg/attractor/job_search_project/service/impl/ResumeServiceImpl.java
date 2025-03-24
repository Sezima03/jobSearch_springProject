package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dao.ResumeDao;
import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.exceptions.ResumeNotFoundException;
import kg.attractor.job_search_project.model.RespondedApplicant;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.service.ResumeService;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;
    private final UserDao userDao;


    @Override
    public void getCreateResume(ResumeDto resumeDto) {
        Resume resume1=new Resume();
        resume1.setId(resumeDto.getId());
        resume1.setName(resumeDto.getName());
        resume1.setCategoryId(resumeDto.getCategoryId());
        resume1.setSalary(resumeDto.getSalary());
        resume1.setActive(resumeDto.isActive());
        resume1.setCreatedDate(resumeDto.getCreatedDate());
        resume1.setUpdateTime(resumeDto.getUpdateTime());

        resumeDao.getCreateResume(resumeDto);
    }

    //TODO Реализован
    @Override
    public void getUpdateResume(Long resumeId, Resume updateResume) {
        resumeDao.getUpdateResume(resumeId, updateResume);
    }

    //TODO реализован
    @Override
    public boolean getDeleteResume(Long resumeId) {
        return resumeDao.deleteResume(resumeId);
    }

    @Override
    public List<ResumeDto> getAllResumeByCategory(String category) {

        List<Resume> resumes=userDao.searchByCategory(category);
        if (resumes!=null && !resumes.isEmpty()){
            return resumes.stream()
                    .map(resume -> ResumeDto.builder()
                            .id(resume.getId())
                            .applicantId(resume.getApplicantId())
                            .name(resume.getName())
                            .categoryId(resume.getCategoryId())
                            .salary(resume.getSalary())
                            .isActive(resume.isActive())
                            .createdDate(resume.getCreatedDate())
                            .updateTime(resume.getUpdateTime())
                            .build())
                    .toList();
        }
        return Collections.emptyList();
    }

    //TODO реализован
    @Override
    public ResumeDto getFindResumeById(Long resumeId){

        Resume resume = resumeDao.findResumeById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);

        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicantId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .build();
    }

    @Override
    public List<RespondedApplicantDto> getresponseVacancy(Long vacancyId) {
        List<RespondedApplicant> respondedApplicants=userDao.responseApplicantToVacancy(vacancyId);

        if (respondedApplicants!=null && !respondedApplicants.isEmpty()){
            return respondedApplicants.stream()
                    .map(response->RespondedApplicantDto.builder()
                            .id(response.getId())
                            .resumeId(response.getResumeId())
                            .vacancyId(response.getVacancyId())
                            .confirmation(response.isConfirmation())
                            .build())
                    .toList();
        }
        return Collections.emptyList();
    }

    //TODO реализован
    @Override
    public List<ResumeDto> getAllResume() {
        List<Resume> resumeList = resumeDao.getResume();
        if(!resumeList.isEmpty()){
            return resumeList.stream()
                    .map(resume -> ResumeDto.builder()
                            .id(resume.getId())
                            .applicantId(resume.getApplicantId())
                            .name(resume.getName())
                            .categoryId(resume.getCategoryId())
                            .salary(resume.getSalary())
                            .isActive(resume.isActive())
                            .createdDate(resume.getCreatedDate())
                            .updateTime(resume.getUpdateTime())
                            .build())
                    .toList();
        }

        return Collections.emptyList();
    }

}