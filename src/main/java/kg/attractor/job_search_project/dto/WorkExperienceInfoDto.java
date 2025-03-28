package kg.attractor.job_search_project.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkExperienceInfoDto {

    private Long id;

    private Long resumeId;

    @NotNull(message = "Год работы не может быть null")
    private Integer year;

    @NotBlank(message = "Название компании не может быть пустым")
    private String companyName;

    @NotBlank(message = "Должность не может быть пустой")
    private String position;

    @NotBlank(message = "Обязанности не могут быть пустыми")
    private String responsibility;
}
