package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query(value = "SELECT * FROM resume r WHERE r.applicant_id = :applicantId", nativeQuery = true)
    List<Resume> findAllByApplicantId(@Param("applicantId") Long applicantId);

    Optional<Resume> findResumeById(Long id);

    @Query("SELECT r FROM Resume r WHERE r.categoryId = :categoryId")
    List<Resume> findByCategoryId(@Param("categoryId") String categoryId);
}
