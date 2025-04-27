package kg.attractor.job_search_project.dto;
import lombok.Data;

@Data
public class WorkExperienceInfoDto {

    private Long id;

    private Long resumeId;

    private Integer year;

    private String companyName;

    private String position;

    private String responsibility;
}
