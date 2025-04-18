package kg.attractor.job_search_project.repository;

import kg.attractor.job_search_project.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo, Long> {
}
