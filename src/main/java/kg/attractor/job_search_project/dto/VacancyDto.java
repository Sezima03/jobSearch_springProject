package kg.attractor.job_search_project.dto;

import jakarta.validation.constraints.*;
import kg.attractor.job_search_project.model.RespondedApplicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {
    private Long id;
    @NotBlank(message = "Название вакансии не должно быть пустым")
    private String name;

    private String description;

    private Long categoryId;

    private Double salary;


    private Integer expFrom;

    private Integer expTo;

    private Boolean isActive;

    @Min(value = 1, message = "Должен быть больше 0")
    private Long authorId;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    private List<RespondedApplicant> response;
}
