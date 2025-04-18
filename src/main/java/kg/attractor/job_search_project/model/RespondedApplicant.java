package kg.attractor.job_search_project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Getter
@Setter
@Entity
@Table(name = "responded_applicant")
public class RespondedApplicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "resume_id")
    private Long resumeId;

    @Column(name = "vacancy_id")
    private Long vacancyId;
    private boolean confirmation;
}
