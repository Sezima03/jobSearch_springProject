package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final UserDao userDao;

    @Override
    public Vacancy createdVacancy(Vacancy vacancy) {
        //TODO входные данные: name, description, category_id, salary, exp_from, exp_to, isActive, author_id, created_date, update_date
        //TODO логика для создании вакансии и сохранение
        //TODO Возвращаем новую ваканисию
        return vacancy;
    }

    @Override
    public Vacancy updateVacancy(Long id, Vacancy updateVacancy) {
        //TODO логика для редактирования вакансии пользователя
        //TODO входные данные: name, description, category_id, salary, exp_from, exp_to, isActive, author_id, created_date, update_date
        //TODO по id ещем соответствующую вакансию и обновляем данные вакансии
        //TODO возращаем обновленную вакансию
        return updateVacancy;
    }

    @Override
    public boolean deleteVacancy(Long id) {
        //TODO Удаляем вакансию по id
        //TODO возвращаем true если вакансия удалена
        return true;
    }

    @Override
    public List<ResumeDto> getAllResume() {
        List<Resume> resumeList = userDao.getResume();
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

    @Override
    public List<VacancyDto> getVacancyByCategory(Long category_id) {
        List<Vacancy> vacancies=userDao.getVacancyByCategory(category_id);
        if (!vacancies.isEmpty()){
            return vacancies.stream()
                    .map(vacancy -> VacancyDto.builder()
                            .id(vacancy.getId())
                            .name(vacancy.getName())
                            .description(vacancy.getDescription())
                            .salary(vacancy.getSalary())
                            .expFrom(vacancy.getExpFrom())
                            .expTo(vacancy.getExpTo())
                            .isActive(vacancy.isActive())
                            .authorId(vacancy.getAuthorId())
                            .createdDate(vacancy.getCreatedDate())
                            .updateTime(vacancy.getUpdateTime())
                            .build())
                    .toList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<VacancyDto> getRespondedToVacancy(Long applicantId) {
        List <Vacancy> vacancies = userDao.responseToVacancies(applicantId);
        if (!vacancies.isEmpty()) {
            return vacancies.stream()
                    .map(vacancy -> VacancyDto.builder()
                            .id(vacancy.getId())
                            .name(vacancy.getName())
                            .description(vacancy.getDescription())
                            .salary(vacancy.getSalary())
                            .expFrom(vacancy.getExpFrom())
                            .expTo(vacancy.getExpTo())
                            .isActive(vacancy.isActive())
                            .authorId(vacancy.getAuthorId())
                            .createdDate(vacancy.getCreatedDate())
                            .updateTime(vacancy.getUpdateTime())
                            .build())
                    .toList();
        }
        return List.of();
    }

    @Override
    public List<VacancyDto> getVacancy(){
        List<Vacancy> vacancies = userDao.getVacancy();
        if (!vacancies.isEmpty()) {
            return vacancies.stream()
                    .map(vacancy -> VacancyDto.builder()
                            .id(vacancy.getId())
                            .name(vacancy.getName())
                            .description(vacancy.getDescription())
                            .salary(vacancy.getSalary())
                            .expFrom(vacancy.getExpFrom())
                            .expTo(vacancy.getExpTo())
                            .isActive(vacancy.isActive())
                            .authorId(vacancy.getAuthorId())
                            .createdDate(vacancy.getCreatedDate())
                            .updateTime(vacancy.getUpdateTime())
                            .build()).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Vacancy> getSearchApplicant(String name) {
        //TODO логика для поиска соискателя
        return List.of();
    }
}