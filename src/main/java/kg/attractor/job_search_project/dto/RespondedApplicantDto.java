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

    @NotNull
    private Long resumeId;

    @NotNull
    private Long vacancyId;

    @NotBlank
    private boolean confirmation;
}
