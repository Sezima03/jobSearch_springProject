package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findByCategoryId(Long category_id);

    boolean existsCategoryById(Long category_id);
    List<Vacancy> findAllVacancyByIsActiveTrue();

    List<Vacancy> findAllByOrderByCreatedDateDesc();
    List<Vacancy> findAllByOrderByCreatedDateAsc();


    @Query("""
            select v from Vacancy v 
            left join v.response r 
            GROUP BY v.id 
            order by count(r.id) desc
            """)
    List<Vacancy> findAllOrderByResponseCount();

    Page<Vacancy> findByAuthorId(Long author_id, Pageable pageable);

    @Query("SELECT v FROM Vacancy v")
    List<Vacancy> allVacancies();


}
