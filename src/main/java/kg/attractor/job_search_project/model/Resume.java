package kg.attractor.job_search_project.model;
import kg.attractor.job_search_project.dto.EducationInfoDto;
import kg.attractor.job_search_project.dto.WorkExperienceInfoDto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Resume {
    private Long id;
    private Long applicantId;
    private String name;
    private Long categoryId;
    private Double salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    private List<EducationInfoDto> educationInfo;

    private List<WorkExperienceInfoDto> workExperienceInfo;

}
