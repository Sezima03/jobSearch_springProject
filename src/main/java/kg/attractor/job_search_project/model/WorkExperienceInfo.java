package kg.attractor.job_search_project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "work_experience_info")
public class WorkExperienceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Column(name = "years")
    private Integer year;

    @Column(name = "company_name")
    private String companyName;
    private String position;

    @Column(name = "responsibilites")
    private String responsibilities;
}
