package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dao.ResumeDao;
import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.*;
import kg.attractor.job_search_project.repository.*;
import kg.attractor.job_search_project.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final EducationInfoRepository educationInfoRepository;
    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    private final ResumeDao resumeDao;
    private final CategoryRepository categoryRepository;


    @Override
    public void getCreateResume(ResumeDto resumeDto) {
        log.info("Creating ResumeControllerApi with name {}",  resumeDto.getName());

        Category category = categoryRepository.findById(resumeDto.getCategoryId())
                .orElseThrow(() -> new JobSearchException("Категория с таким id не существует"));
        Resume resume1=new Resume();
        resume1.setId(resumeDto.getId());
        resume1.setApplicantId(resumeDto.getApplicantId());
        resume1.setName(resumeDto.getName());
        resume1.setCategoryId(category);
        resume1.setSalary(resumeDto.getSalary());
        resume1.setActive(resumeDto.isActive());

         resumeRepository.save(resume1);
         log.info("Created ResumeControllerApi with name {}",  resumeDto.getName());

         if (resumeDto.getEducationInfo() != null) {
             for (EducationInfoDto educationInfoDto:resumeDto.getEducationInfo()) {
                 EducationInfo educationInfo = new EducationInfo();
                 educationInfo.setId(educationInfoDto.getId());
                 educationInfo.setResume(resume1);
                 educationInfo.setInstitution(educationInfoDto.getInstitution());
                 educationInfo.setProgram(educationInfoDto.getProgram());

                 educationInfo.setDegree(educationInfoDto.getDegree());

                 educationInfoRepository.save(educationInfo);
                 log.info("Education info for resume Id {}", resumeDto.getId());
         }
        }

         if (resumeDto.getWorkExperienceInfo() != null) {
             for (WorkExperienceInfoDto workExperienceInfoDto:resumeDto.getWorkExperienceInfo()) {
                 WorkExperienceInfo workExperienceInfo1 = new WorkExperienceInfo();
                 workExperienceInfo1.setResume(resume1);
                 workExperienceInfo1.setYear(workExperienceInfoDto.getYear());
                 workExperienceInfo1.setCompanyName(workExperienceInfoDto.getCompanyName());
                 workExperienceInfo1.setPosition(workExperienceInfoDto.getPosition());
                 workExperienceInfo1.setResponsibilities(workExperienceInfoDto.getResponsibility());

                 workExperienceInfoRepository.save(workExperienceInfo1);
                 log.info("WorkExperience info for resume Id {}", resumeDto.getId());
         }
        }
    }

    @Override
    public void getUpdateResume(Long resumeId, ResumeDto updateResume) {
        log.info("Updating ResumeControllerApi with id {}",  resumeId);

        Resume resume =resumeRepository.findById(resumeId)
                .orElseThrow(()->new JobSearchException("Resume not found"));

        Category category = categoryRepository.findById(updateResume.getCategoryId())
                .orElseThrow(() -> new JobSearchException("Category not found"));

        resume.setApplicantId(updateResume.getApplicantId());
        resume.setName(updateResume.getName());
        resume.setCategoryId(category);
        resume.setSalary(updateResume.getSalary());
        resume.setActive(updateResume.isActive());

        resumeRepository.save(resume);
        log.info("Resume with id {} updated successfully",  resumeId);

        for (EducationInfoDto eduDto:updateResume.getEducationInfo()) {
            EducationInfo educationInfo = educationInfoRepository.findById(eduDto.getId())
                            .orElseThrow(()->new JobSearchException("Education not found"));
            educationInfo.setResume(resume);
            educationInfo.setInstitution(eduDto.getInstitution());
            educationInfo.setProgram(eduDto.getProgram());
            educationInfo.setStartDate(eduDto.getStartDate());
            educationInfo.setEndDate(eduDto.getEndDate());
            educationInfo.setDegree(eduDto.getDegree());

            educationInfoRepository.save(educationInfo);
            log.info("Education info for resume Id {} updated successfully", resumeId);
        }

        for (WorkExperienceInfoDto wei : updateResume.getWorkExperienceInfo()) {
            WorkExperienceInfo workExperienceInfo = workExperienceInfoRepository.findById(wei.getId())
                    .orElseThrow(()->new JobSearchException("WorkExperience info not found"));

            workExperienceInfo.setResume(resume);
            workExperienceInfo.setYear(wei.getYear());
            workExperienceInfo.setCompanyName(wei.getCompanyName());
            workExperienceInfo.setPosition(wei.getPosition());
            workExperienceInfo.setResponsibilities(wei.getResponsibility());
            workExperienceInfoRepository.save(workExperienceInfo);
            log.info("WorkExperience info for resume Id {} updated successfully", resumeId);
        }

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

        List<Resume> resumes = resumeDao.getAllResumeByCategory(category);
        if (resumes==null||resumes.isEmpty()){
            log.warn("All ResumeControllerApi by category {} is empty",category);
            throw new JobSearchException("ResumeControllerApi Not Found");
        }
        log.info("All ResumeControllerApi {} found successfully", category);
        return resumes.stream()
                .map(resume -> ResumeDto.builder()
                        .id(resume.getId())
                        .applicantId(resume.getApplicantId())
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
                .applicantId(resume.getApplicantId())
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
                        .applicantId(resume.getApplicantId())
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
                        .applicantId(resume.getApplicantId())
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
                        .applicantId(resume.getApplicantId())
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId().getId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .createdDate(resume.getCreatedDate())
                        .updateTime(resume.getUpdateTime())
                        .build())
                .toList();

    }

}