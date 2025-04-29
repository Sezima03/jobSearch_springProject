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

    private Long applicantId;

    @NotBlank(message = "Имя резюме не должно быть пустым")
    private String name;

    @NotNull
    private Long categoryId;

    @NotNull
    private Double salary;

    private boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updateTime;

    private List<EducationInfoDto> educationInfo;

    private List<WorkExperienceInfoDto> workExperienceInfo;
}
