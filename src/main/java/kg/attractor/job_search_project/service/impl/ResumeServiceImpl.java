package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.dto.ResumeDto;
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
    public Resume getCreateResume(Resume resume) {
        //TODO входные данные applicant_id, name, category_id, salary, isActive, created_date, update_date
        //TODO логика создание резюме
        //TODO возвращаем новое резюме
        return resume;
    }

    @Override
    public Resume getUpdateResume(Long resumeId, Resume updateResume) {
        //TODO входные данные applicant_id, name, category_id, salary, isActive, created_date, update_date
        //TODO По id обновляем резбме
        //TODO возвращаем обновленное резюме

        return updateResume;
    }


    @Override
    public boolean getDeleteResume(Long resumeId) {
        //TODO Удаляем резюме по id
        //TODO возвращаем true если вакансия удалена
        return true;
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
    public String getresponceVacancy(Long resumeId, Long vacancyId) {


        return "Резюме с ID " + resumeId + "откликнулось на вакансию с ID " + vacancyId;
    }

    @Override
    public List<Vacancy> getSearchForAnEmployer(String name) {
        //TODO логика поиска работодателя

        return List.of();
    }
}
