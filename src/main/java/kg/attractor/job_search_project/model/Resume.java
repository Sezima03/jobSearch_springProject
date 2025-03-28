package kg.attractor.job_search_project.model;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

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

}
