package kg.attractor.job_search_project.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondedApplicantDto {
    private Long id;

    @NotNull(message = "id не может быть null")
    @Min(value = 1, message = "Должен быть больше 0")
    private Long resumeId;

    @NotNull(message = "id не может быть null")
    @Min(value = 1, message = "Должен быть больше 0")
    private Long vacancyId;

    @NotBlank
    private boolean confirmation;
}
