package kg.attractor.job_search_project.repository;

import kg.attractor.job_search_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryNameById(Long id);
}
