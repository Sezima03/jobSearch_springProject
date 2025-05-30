package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r WHERE r.applicantId.id = :applicantId")
    Page<Resume> findAllByApplicantId(@Param("applicantId") Long applicantId, Pageable pageable);

    Optional<Resume> findResumeById(Long id);

    @Query("SELECT r FROM Resume r WHERE r.categoryId = :categoryId")
    List<Resume> findByCategoryId(@Param("categoryId") String categoryId);

    @Query(value = "SELECT * FROM resume r WHERE r.applicant_id = :applicantId", nativeQuery = true)
    List<Resume> byApplicantId(@Param("applicantId") Long applicantId);
}
