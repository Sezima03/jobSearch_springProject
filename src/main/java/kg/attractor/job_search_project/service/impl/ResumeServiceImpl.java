package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.model.RespondedApplicant;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

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

        userDao.getCreateResume(resumeDto);
    }

    @Override
    public void getUpdateResume(Long resumeId, ResumeDto updateResume) {
        userDao.getupdateResume(resumeId, updateResume);
    }


    @Override
    public boolean getDeleteResume(Long resumeId) {
        return userDao.getdeleteResume(resumeId);
    }

    @Override
    public List<ResumeDto> getAllVacancyByCategory(String category) {

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

    @Override
    public List<ResumeDto> getUserById(Long userId){
        List<Resume> resumes=userDao.getUserById(userId);
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

    @Override
    public List<Vacancy> getSearchForAnEmployer(String name) {
        //TODO логика поиска работодателя

        return List.of();
    }
}