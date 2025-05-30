package kg.attractor.job_search_project.dto;
import jakarta.validation.constraints.*;
import kg.attractor.job_search_project.model.RespondedApplicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {
    private Long id;

    @NotBlank(message = "{vacancy.dto.name}")
    private String name;

    @NotBlank(message = "{vacancy.dto.description}")
    private String description;

    @NotNull(message = "{vacancy.dto.categoryId}")
    private Long categoryId;

    @NotNull(message = "{vacancy.dto.salary}")
    private Double salary;

    private Integer expFrom;

    private Integer expTo;

    private Boolean isActive;

    private Long authorId;

    private LocalDate createdDate;
    private LocalDate updateTime;

    private List<RespondedApplicant> response;
}
