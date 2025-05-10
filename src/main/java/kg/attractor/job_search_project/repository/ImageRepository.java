package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<UserImage,Long> {
    UserImage findByUserId(long id);

}
