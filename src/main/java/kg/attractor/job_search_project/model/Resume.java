package kg.attractor.job_search_project.model;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Resume {
    private Long id;
    private int applicantId;
    private String name;
    private int categoryId;
    private Double salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

}
