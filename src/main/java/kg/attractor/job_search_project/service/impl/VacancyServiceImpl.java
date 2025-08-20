package kg.attractor.job_search_project.service.impl;
import kg.attractor.job_search_project.dto.RespondedApplicantDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.repository.VacancyRepository;
import kg.attractor.job_search_project.service.ResponsesApplicantService;
import kg.attractor.job_search_project.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final @Lazy ResponsesApplicantService responsesApplicantService;

    @Override
    public void createdVacancy(VacancyDto vacancyDto) {
        log.info("Creating Vacancy : {}", vacancyDto.getName());

        boolean existsCategory = vacancyRepository.existsCategoryById(vacancyDto.getCategoryId());
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
        vacancy.setIsActive(vacancyDto.getIsActive());
        vacancy.setAuthorId(vacancyDto.getAuthorId());

        vacancyRepository.save(vacancy);
        log.info("Vacancy Created : {}", vacancy.getName());
    }


    @Override
    public void getUpdateVacancy(VacancyDto vacancyDto, Long id) {
        log.info("Updating Vacancy with id: {}", id);
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found with id: " + id));
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setCategoryId(vacancyDto.getCategoryId());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setExpFrom(vacancyDto.getExpFrom());
        vacancy.setExpTo(vacancyDto.getExpTo());
        vacancy.setIsActive(vacancyDto.getIsActive());
        vacancy.setAuthorId(vacancyDto.getAuthorId());
        vacancyRepository.save(vacancy);
        log.info("Vacancy Updated: {}", id);
    }


    @Override
    public boolean deleteVacancy(Long id) {

        if(!vacancyRepository.existsById(id)) {
            throw new JobSearchException("Vacancy not found");
        }

        vacancyRepository.deleteById(id);
        return true;
    }

    @Override
    public List<VacancyDto> getVacancyByCategory(Long category_id) {
        log.info("Getting Vacancy by category : {}", category_id);
        List<Vacancy> vacancies=vacancyRepository.findByCategoryId(category_id);
        if (vacancies.isEmpty()){
            throw new JobSearchException("Vacancy Not Found");
        }

        log.info("Vacancies found for category Id {}", category_id);
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
    public List<VacancyDto> getAllVacancy(){
        List<Vacancy> vacancies=vacancyRepository.allVacancies();
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
    public List<VacancyDto> getVacancySortDesc(){
        log.info("Getting Vacancy");
        List<Vacancy> vacancies = vacancyRepository.findAllByOrderByCreatedDateDesc()  ;
        if (vacancies.isEmpty()) {
            throw new JobSearchException("Vacancy Not Found");
        }

        log.info("Vacancies found : {}", vacancies.size());
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
    public List<VacancyDto> getVacancySortAsc(){
        List<Vacancy> vacancies = vacancyRepository.findAllByOrderByCreatedDateAsc();
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
        log.info("Getting RespondedApplicant by Vacancy Id : {}", vacancyId);
        List<RespondedApplicantDto> respondedApplicants = responsesApplicantService.findAllVacancyById(vacancyId);
        if ((respondedApplicants == null) || (respondedApplicants.isEmpty())) {
            throw new JobSearchException("No applicants found");
        }
        log.info("Found RespondedApplicant by Vacancy Id : {}", vacancyId);
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
        log.info("Getting Vacancies");
        List<Vacancy> vacancies = vacancyRepository.findAllVacancyByIsActiveTrue();
        if (vacancies.isEmpty() || vacancies == null) {
            throw new JobSearchException("Vacancy Not Found");
        }

        log.info("Found Vacancies : {}", vacancies.size());
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
    public VacancyDto getFindVacancyById(Long vacancyId){
        log.info("Retrieving VacancyControllerApi with id {}", vacancyId);
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(()->new JobSearchException("Vacancy Not Found"));

        log.info("Found Vacancy with Id: {}", vacancyId);

        return VacancyDto.builder()
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
                .build();
    }

    @Override
    public Page<VacancyDto> getAllVacancyByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updateTime").descending());
        Page<Vacancy> vacancyPage = vacancyRepository.findByAuthorId(userId, pageable);
        return vacancyPage.map(vac -> VacancyDto.builder()
                .id(vac.getId())
                .name(vac.getName())
                .description(vac.getDescription())
                .categoryId(vac.getCategoryId())
                .salary(vac.getSalary())
                .expFrom(vac.getExpFrom())
                .expTo(vac.getExpTo())
                .isActive(vac.getIsActive())
                .authorId(vac.getAuthorId())
                .createdDate(vac.getCreatedDate())
                .updateTime(vac.getUpdateTime())
                .build());
    }



    @Override
    public void getUpdateVacancyDate(Long vacancyId){
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(()->new JobSearchException("Vacancy Not Found"));

        vacancy.setUpdateTime(vacancy.getUpdateTime());
        vacancyRepository.save(vacancy);
    }


}