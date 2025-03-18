package kg.attractor.job_search_project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String saveImage(MultipartFile file);

    ResponseEntity<?> getByName(String imageName);
}
