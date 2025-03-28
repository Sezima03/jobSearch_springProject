package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.dao.VacancyDao;
import kg.attractor.job_search_project.dao.existenceCheck.ExistenceCheckDao;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.RespondedApplicant;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;
    private final ExistenceCheckDao  existenceCheckDao;

    @Override
    public void createdVacancy(VacancyDto vacancyDto) {

        boolean authorsExist = existenceCheckDao.existsByAuthorId(vacancyDto.getAuthorId());

        if (!authorsExist) {
            throw new JobSearchException("Автор с таким id не существует");
        }

        boolean existsCategory = existenceCheckDao.existaCategoryId(vacancyDto.getCategoryId());
        if (!existsCategory) {
            throw new JobSearchException("Категория с таким id не существует");
        }

        Vacancy vacancy=new Vacancy();
        vacancy.setId(vacancyDto.getId());
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setCategoryId(vacancyDto.getCategoryId());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setExpFrom(vacancyDto.getExpFrom());
        vacancy.setExpTo(vacancyDto.getExpTo());
        vacancy.setIsActive(vacancyDto.isActive());
        vacancy.setAuthorId(vacancyDto.getAuthorId());

        vacancyDao.getCreateVacancy(vacancy);
    }

    @Override
    public void getUpdateVacancy(VacancyDto vacancyDto, Long id) {
        vacancyDao.getUpdateVacancy(vacancyDto, id);
    }

    @Override
    public boolean deleteVacancy(Long id) {
        return vacancyDao.getDeleteVacancy(id);
    }

    @Override
    public List<VacancyDto> getVacancyByCategory(Long category_id) {
        List<Vacancy> vacancies=vacancyDao.getVacancyByCategory(category_id);
        if (vacancies.isEmpty()){
            throw new JobSearchException("Vacancy Not Found");
        }
        return vacancies.stream()
                .map(vacancy -> VacancyDto.builder()
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .categoryId(vacancy.getCategoryId())
                        .salary(vacancy.getSalary())
                        .expFrom(vacancy.getExpFrom())
                        .expTo(vacancy.getExpTo())
                        .isActive(vacancy.getIsActive())
                        .authorId(vacancy.getAuthorId())
                        .createdDate(vacancy.getCreatedDate())
                        .updateTime(vacancy.getUpdateTime())
                        .build())
                .toList();
    }

    @Override
    public List<VacancyDto> getVacancy(){
        List<Vacancy> vacancies = vacancyDao.getVacancies();
        if (vacancies.isEmpty()) {
            throw new JobSearchException("Vacancy Not Found");
        }
        return vacancies.stream()
                .map(vacancy -> VacancyDto.builder()
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .categoryId(vacancy.getCategoryId())
                        .salary(vacancy.getSalary())
                        .expFrom(vacancy.getExpFrom())
                        .expTo(vacancy.getExpTo())
                        .isActive(vacancy.getIsActive())
                        .authorId(vacancy.getAuthorId())
                        .createdDate(vacancy.getCreatedDate())
                        .updateTime(vacancy.getUpdateTime())
                        .build()).toList();
    }

    @Override
    public List<RespondedApplicantDto>  getRespondedApplicantByVacancyId(Long vacancyId) {
        List<RespondedApplicant> respondedApplicants = vacancyDao.getFindRespondedApplicantByVacancyId(vacancyId);
        if ((respondedApplicants == null) || (respondedApplicants.isEmpty())) {
            throw new JobSearchException("No applicants found");
        }
        return respondedApplicants.stream()
                .map(respondedApplicant -> RespondedApplicantDto.builder()
                        .id(respondedApplicant.getId())
                        .resumeId(respondedApplicant.getResumeId())
                        .vacancyId(respondedApplicant.getVacancyId())
                        .confirmation(respondedApplicant.isConfirmation())
                        .build())
                .toList();
    }

    @Override
    public List<VacancyDto> getAllActiveVacancies() {
        List<Vacancy> vacancies = vacancyDao.getAllActiveVacancies();
        if (vacancies.isEmpty() || vacancies == null) {
            throw new JobSearchException("Vacancy Not Found");
        }
        return vacancies.stream()
                .map(vacancy -> VacancyDto.builder()
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .categoryId(vacancy.getCategoryId())
                        .salary(vacancy.getSalary())
                        .expFrom(vacancy.getExpFrom())
                        .expTo(vacancy.getExpTo())
                        .isActive(vacancy.getIsActive())
                        .authorId(vacancy.getAuthorId())
                        .createdDate(vacancy.getCreatedDate())
                        .updateTime(vacancy.getUpdateTime())
                        .build())
                .toList();
    }

}