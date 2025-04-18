package kg.attractor.job_search_project.repository;

import kg.attractor.job_search_project.model.EducationInfo;
import kg.attractor.job_search_project.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationInfoRepository extends JpaRepository<EducationInfo, Long> {
}
