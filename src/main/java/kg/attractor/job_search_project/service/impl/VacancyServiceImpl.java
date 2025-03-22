package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.dto.VacancyDto;
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
    public void createdVacancy(VacancyDto vacancyDto) {

        Vacancy vacancy=new Vacancy();
        vacancy.setId(vacancyDto.getId());
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setCategoryId(vacancyDto.getCategoryId());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setExpFrom(vacancyDto.getExpFrom());
        vacancy.setExpTo(vacancyDto.getExpTo());
        vacancy.setActive(vacancyDto.isActive());
        vacancy.setAuthorId(vacancyDto.getAuthorId());

        userDao.getCreatedVacancy(vacancyDto);
    }

    @Override
    public void updateVacancy(VacancyDto vacancyDto, Long id) {
        userDao.getUpdateVacancy(vacancyDto, id);
    }

    @Override
    public boolean deleteVacancy(Long id) {
        return userDao.getdeleteVacancy(id);
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

}