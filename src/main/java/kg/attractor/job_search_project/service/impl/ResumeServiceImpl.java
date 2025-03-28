package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dao.ResumeDao;
import kg.attractor.job_search_project.dao.existenceCheck.ExistenceCheckDao;
import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.EducationInfo;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.WorkExperienceInfo;
import kg.attractor.job_search_project.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;
    private final ExistenceCheckDao  existenceCheckDao;

    @Override
    public void getCreateResume(ResumeDto resumeDto) {

        boolean existsCategory = existenceCheckDao.existaCategoryId(resumeDto.getCategoryId());
        if (!existsCategory) {
            throw new JobSearchException("Категория с таким id не существует");
        }
        Resume resume1=new Resume();
        resume1.setId(resumeDto.getId());
        resume1.setName(resumeDto.getName());
        resume1.setCategoryId(resumeDto.getCategoryId());
        resume1.setSalary(resumeDto.getSalary());
        resume1.setActive(resumeDto.isActive());
        resume1.setCreatedDate(resumeDto.getCreatedDate());
        resume1.setUpdateTime(resumeDto.getUpdateTime());

         resumeDao.getCreateResume(resumeDto);

        for (EducationInfoDto educationInfoDto:resumeDto.getEducationInfo()) {
            EducationInfo educationInfo = new EducationInfo();
            educationInfo.setId(educationInfoDto.getId());
            educationInfo.setResumeId(resumeDto.getId());
            educationInfo.setInstitution(educationInfoDto.getInstitution());
            educationInfo.setProgram(educationInfoDto.getProgram());
            educationInfo.setStartDate(educationInfoDto.getStartDate());
            educationInfo.setEndDate(educationInfoDto.getEndDate());
            educationInfo.setDegree(educationInfoDto.getDegree());
            resumeDao.getCreateEduInfo(educationInfoDto);
        }

        for (WorkExperienceInfoDto workExperienceInfoDto:resumeDto.getWorkExperienceInfo()) {
            WorkExperienceInfo workExperienceInfo1 = new WorkExperienceInfo();
            workExperienceInfo1.setResumeId(resumeDto.getId());
            workExperienceInfo1.setYear(workExperienceInfoDto.getYear());
            workExperienceInfo1.setCompanyName(workExperienceInfoDto.getCompanyName());
            workExperienceInfo1.setPosition(workExperienceInfoDto.getPosition());
            workExperienceInfo1.setResponsibilities(workExperienceInfoDto.getResponsibility());

            resumeDao.getCreateWorkExperienceInfo(workExperienceInfoDto);
        }
    }

    @Override
    public void getUpdateResume(Long resumeId, ResumeDto updateResume) {
        resumeDao.getUpdateResume(resumeId, updateResume);
    }

    @Override
    public boolean getDeleteResume(Long resumeId) {
        return resumeDao.deleteResume(resumeId);
    }

    @Override
    public List<ResumeDto> getAllResumeByCategory(String category) {

        List<Resume> resumes=resumeDao.getAllResumeByCategory(category);
        if (resumes==null||resumes.isEmpty()){
            throw new JobSearchException("Resume Not Found");
        }
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


    @Override
    public ResumeDto getFindResumeById(Long resumeId){

        Resume resume = resumeDao.findResumeById(resumeId)
                .orElseThrow(()->new JobSearchException("Resume Not Found"));

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
    public List<ResumeDto> getAllResume() {
        List<Resume> resumeList = resumeDao.getResume();
        if (resumeList==null || resumeList.isEmpty()){
            throw new JobSearchException("Resume Not Found");
        }
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

    @Override
    public List<ResumeDto> getAllResumeByApplicantId(Long applicantId) {
        List<Resume> resumes = resumeDao.getAllResumeByApplicantId(applicantId);
        if (resumes==null||resumes.isEmpty()){
            throw new JobSearchException("Resume Not Found");
        }
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

}