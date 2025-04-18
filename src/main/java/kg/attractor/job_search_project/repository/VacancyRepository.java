package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findByCategoryId(Long category_id);

    boolean existsCategoryById(Long category_id);
    List<Vacancy> findAllVacancyByIsActiveTrue();
}
