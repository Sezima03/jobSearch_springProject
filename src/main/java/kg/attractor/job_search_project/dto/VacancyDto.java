package kg.attractor.job_search_project.dto;

import jakarta.persistence.Column;
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

    @NotBlank(message = "Поле не может быть пустым")
    private String description;

    @NotNull(message = "Поле не может быть пустым")
    private Long categoryId;

    @NotNull(message = "Укажите зарплату")
    private Double salary;

    @NotNull(message = "Поле не может быть пустым")
    private Integer expFrom;

    @NotNull(message = "Поле не может быть пустым")
    private Integer expTo;

    private Boolean isActive;

    private Long authorId;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    private List<RespondedApplicant> response;
}
