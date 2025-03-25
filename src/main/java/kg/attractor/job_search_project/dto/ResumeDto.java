package kg.attractor.job_search_project.dto;

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
    private int applicantId;
    private String name;
    private int categoryId;
    private Double salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private List<EducationInfoDto> educationInfo;
    private List<WorkExperienceInfoDto> workExperienceInfo;
}
