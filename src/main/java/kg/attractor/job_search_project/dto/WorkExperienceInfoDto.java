package kg.attractor.job_search_project.dto;
import lombok.Data;

@Data
public class WorkExperienceInfoDto {
    private Long id;
    private int resumeId;
    private int year;
    private String companyName;
    private String position;
    private String responsibility;
}
