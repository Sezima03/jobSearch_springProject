package kg.attractor.job_search_project.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Дата начала не может быть null")
    private LocalDate startDate;

    @NotNull(message = "Дата окончания не может быть null")
    private LocalDate endDate;

    private String degree;
}
