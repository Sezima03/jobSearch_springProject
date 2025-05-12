package kg.attractor.job_search_project.repository;

import kg.attractor.job_search_project.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
    List<RespondedApplicant> findAllByVacancyId(Long vacancyId);
    int countRespondedApplicantByUserId(Long userId);
    int countRespondedApplicantByVacancyIdIn(List<Long> vacancyIds);
    List<RespondedApplicant> findAllRespondedApplicantByUserId(Long userId);

    List<RespondedApplicant> findByVacancyIdIn(List<Long> vacancyId);
    Optional<RespondedApplicant> findResumeByUserId(Long userId);
}
