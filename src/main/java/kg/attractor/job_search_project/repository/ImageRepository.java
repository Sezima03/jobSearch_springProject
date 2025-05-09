package kg.attractor.job_search_project.repository;
import kg.attractor.job_search_project.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<UserImage,Long> {
    Optional<UserImage> findByUserId(long id);

    @Query("SELECT u FROM UserImage u WHERE u.id = :userId")
    UserImage getImageByUserId(@Param("userId") Long userId);
}
