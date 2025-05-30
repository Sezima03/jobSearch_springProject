package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
    List<RespondedApplicant> findAllByVacancyId(Long vacancyId);
    int countRespondedApplicantByUserId(Long userId);
    List<RespondedApplicant> findAllRespondedApplicantByUserId(Long userId);

    @Query("select ra from RespondedApplicant ra")
    List<RespondedApplicant> findAllRespondedApplicant();

}
