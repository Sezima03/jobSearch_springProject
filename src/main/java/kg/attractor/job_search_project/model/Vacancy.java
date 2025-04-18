package kg.attractor.job_search_project.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "vacancyusr")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;

    @Column(name = "category_id")
    private Long categoryId;

    private Double salary;

    @Column(name = "exp_from")
    private Integer expFrom;

    @Column(name = "exp_to")
    private Integer expTo;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
