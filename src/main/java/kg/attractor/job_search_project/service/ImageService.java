package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.UserImageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String saveImage(UserImageDto userImageDto);

    ResponseEntity<?> getByName(String imageName);

    ResponseEntity<?> findById(long id);
}
