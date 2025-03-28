package kg.attractor.job_search_project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserImageDto {
    private MultipartFile file;
    private Long userId;
}
