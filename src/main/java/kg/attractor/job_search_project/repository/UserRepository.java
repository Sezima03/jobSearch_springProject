package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    List<User>findAllByName(String name);

    List<User> searchByPhoneNumber(String phoneNumber);

    List<User> searchByEmail(String email);

    @Query(value = "select * from vacancyusr v " +
            "where v.id in(select ra.vacancy_id " +
            "from responded_applicant ra " +
            "JOIN resume r ON r.id = ra.resume_id " +
            "where applicant_id = ?)", nativeQuery = true)
    List<Vacancy> findAllRespondedByApplicantId(Long applicantId);

    User findByEmail(String email);
}
