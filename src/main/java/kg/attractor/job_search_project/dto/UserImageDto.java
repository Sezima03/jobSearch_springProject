package kg.attractor.job_search_project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserImageDto {
    private MultipartFile file;
    @NotNull
    @Min(value = 1, message = "Должен быть больше 0")
    private Long userId;
}
