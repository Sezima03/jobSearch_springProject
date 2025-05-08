package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<UserImage,Long> {
    Optional<UserImage> findByUserId(long id);
}
