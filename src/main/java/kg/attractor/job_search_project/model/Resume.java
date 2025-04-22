package kg.attractor.job_search_project.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "applicant_id")
    private Long applicantId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
    private Double salary;

    @Column(name = "is_active")
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationInfo> educationInfo;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperienceInfo> workExperienceInfo;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespondedApplicant> response;

}
