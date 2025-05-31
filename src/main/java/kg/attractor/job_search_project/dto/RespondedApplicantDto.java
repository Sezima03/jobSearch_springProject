package kg.attractor.job_search_project.dto;
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

    private Long userId;

    @NotNull
    private Long resumeId;

    private String resumeName;

    @NotNull
    private Long vacancyId;

    private String vacancyName;


    @NotBlank
    private boolean confirmation;
}
