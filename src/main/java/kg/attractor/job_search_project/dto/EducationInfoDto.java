package kg.attractor.job_search_project.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EducationInfoDto {
    private Long id;
    private Long resumeId;

    @NotBlank(message = "Учебное заведение не может быть пустым")
    private String institution;

    @NotBlank(message = "Программа обучения не может быть пустой")
    private String program;

    private LocalDate startDate;

    private LocalDate endDate;

    private String degree;
}
