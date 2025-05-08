package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.UserImageDto;
import kg.attractor.job_search_project.model.UserImage;
import org.springframework.http.ResponseEntity;

public interface ImageService {
    String saveImage(UserImageDto userImageDto, Long userId);

    ResponseEntity<?> getByName(String imageName);

    ResponseEntity<?> findById(long id);

    UserImage getImageByUserId(Long userId);
}
