package kg.attractor.job_search_project.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EducationInfoDto {
    private Long id;
    private Long resumeId;

    private String institution;

    private String program;

    private LocalDate startDate;

    private LocalDate endDate;

    private String degree;
}
