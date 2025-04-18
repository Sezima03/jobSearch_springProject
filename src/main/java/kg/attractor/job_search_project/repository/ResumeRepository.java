package kg.attractor.job_search_project.repository;

import kg.attractor.job_search_project.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

//    List<Resume> findAllCategory(String categoryName);
    List<Resume> findAllByApplicantId(Long applicantId);
}
