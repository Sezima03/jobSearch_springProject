package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dao.ResumeDao;
import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.*;
import kg.attractor.job_search_project.repository.*;
import kg.attractor.job_search_project.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final EducationInfoService educationInfoService;
    private final UserService userService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final CategoryService categoryService;


    @Override
    public void getCreateResume(ResumeDto resumeDto) {
        log.info("Creating ResumeControllerApi with name {}",  resumeDto.getName());

        Category category = categoryService.findCategoryById(resumeDto.getCategoryId());
        User user = userService.getFindById(resumeDto.getApplicantId());


        Resume resume1=new Resume();
        resume1.setId(resumeDto.getId());
        resume1.setApplicantId(user);
        resume1.setName(resumeDto.getName());
        resume1.setCategoryId(category);
        resume1.setSalary(resumeDto.getSalary());
        resume1.setActive(resumeDto.isActive());

         resumeRepository.save(resume1);
         log.info("Created ResumeControllerApi with name {}",  resumeDto.getName());

         if (resumeDto.getEducationInfo() != null) {
             educationInfoService.saveEducationInfo(resume1, resumeDto.getEducationInfo());
        }

         if (resumeDto.getWorkExperienceInfo() != null) {
             workExperienceInfoService.saveWorkExperienceInfo(resume1, resumeDto.getWorkExperienceInfo());
        }
    }

    @Override
    public void getUpdateResume(Long resumeId, ResumeDto updateResume) {
        log.info("Updating ResumeControllerApi with id {}",  resumeId);

        Resume resume =resumeRepository.findById(resumeId)
                .orElseThrow(()->new JobSearchException("Resume not found"));

        Category category = categoryService.findCategoryById(updateResume.getCategoryId());
        resume.setName(updateResume.getName());
        resume.setCategoryId(category);
        resume.setSalary(updateResume.getSalary());
        resume.setActive(updateResume.isActive());

        resumeRepository.save(resume);
        log.info("Resume with id {} updated successfully",  resumeId);

            educationInfoService.updateEducationInfo(resume, (EducationInfoDto) updateResume.getEducationInfo());


            workExperienceInfoService.updateWorkExperienceInfo(resume, (WorkExperienceInfoDto) updateResume.getWorkExperienceInfo());

    }

    @Override
    public boolean getDeleteResume(Long resumeId) {
        if (resumeRepository.existsById(resumeId)) {
            resumeRepository.deleteById(resumeId);
            return true;
        }
        return false;
    }

    @Override
    public List<ResumeDto> getAllResumeByCategory(String category) {
        log.info("Getting All ResumeControllerApi by category {}", category);

        List<Resume> resumes = resumeRepository.findByCategoryId(category);
        if (resumes==null||resumes.isEmpty()){
            log.warn("All ResumeControllerApi by category {} is empty",category);
            throw new JobSearchException("ResumeControllerApi Not Found");
        }
        log.info("All ResumeControllerApi {} found successfully", category);
        return resumes.stream()
                .map(resume -> ResumeDto.builder()
                        .id(resume.getId())
                        .applicantId(resume.getApplicantId().getId())
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId().getId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .createdDate(resume.getCreatedDate())
                        .updateTime(resume.getUpdateTime())
                        .build())
                .toList();
    }


    @Override
    public ResumeDto getFindResumeById(Long resumeId){
        log.info("Retrieving ResumeControllerApi with id {}", resumeId);

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new JobSearchException("ResumeControllerApi Not Found"));

        log.info("Found resume with Id: {}", resumeId);
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicantId().getId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId().getId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .build();
    }

    @Override
    public List<ResumeDto> getAllResume() {

        List<Resume> resumeList =resumeRepository.findAll();
        if (resumeList==null||resumeList.isEmpty()){
            throw new JobSearchException("Resume Not Found");
        }

        return resumeList.stream()
                .map(resume -> ResumeDto.builder()
                                .id(resume.getId())
                        .applicantId(resume.getApplicantId().getId())
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId().getId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .createdDate(resume.getCreatedDate())
                        .updateTime(resume.getUpdateTime())
                        .build())
                .toList();

    }

    @Override
    public List<ResumeDto> getAllResumeByApplicantId(Long applicantId) {
        log.info("Retrieving Resumes by applicantId {}", applicantId);
        List<Resume> resumes = resumeRepository.findAllByApplicantId(applicantId);
        if (resumes==null||resumes.isEmpty()){
            throw new JobSearchException("ResumeControllerApi Not Found");
        }
        log.info("Found resumes for applicant Id: {}", applicantId);
        return resumes.stream()
                .map(resume -> ResumeDto.builder()
                        .id(resume.getId())
                        .applicantId(resume.getApplicantId().getId())
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId().getId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .createdDate(resume.getCreatedDate())
                        .updateTime(resume.getUpdateTime())
                        .build())
                .toList();
    }

    @Override
    public List<ResumeDto> getAllResumeByUserId(Long userId) {

        List<Resume> resumeList =resumeRepository.findAllByApplicantId(userId);

        return resumeList.stream()
                .map(resume -> ResumeDto.builder()
                        .id(resume.getId())
                        .applicantId(resume.getApplicantId().getId())
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId().getId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .createdDate(resume.getCreatedDate())
                        .updateTime(resume.getUpdateTime())
                        .build())
                .toList();

    }


    @Override
    public void getResumeUpdateDate(Long resumeId){
        log.info("Retrieving Resume with id {}", resumeId);
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new JobSearchException("Resume Not Found"));

        resume.setUpdateTime(LocalDateTime.now());
        resumeRepository.save(resume);
        log.info("Updated Resume with Id: {}", resumeId);

    }


    @Override
    public Resume getFindResumeByID(Long resumeId){
        log.info("Поиск резюме по ID: {}", resumeId);
        return resumeRepository.findResumeById(resumeId)
                .orElseThrow(() -> new JobSearchException("Resume Not Found"));
    }

}