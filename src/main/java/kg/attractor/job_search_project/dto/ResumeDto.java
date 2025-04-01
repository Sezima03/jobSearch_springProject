package kg.attractor.job_search_project.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
public class ResumeDto {
    private Long id;

    @NotNull
    @Min(value = 1, message = "Должен быть больше 0")
    private Long applicantId;

    @NotBlank(message = "Имя резюме не должно быть пустым")
    private String name;

    @NotNull
    @Min(value = 1, message = "Должен быть больше 0")
    private Long categoryId;

    private Double salary;

    @NotNull(message = "Поле 'isActive' не может быть null")
    private boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updateTime;

    @Valid
    private List<EducationInfoDto> educationInfo;

    @Valid
    private List<WorkExperienceInfoDto> workExperienceInfo;
}
