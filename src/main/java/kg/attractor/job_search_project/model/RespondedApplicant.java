package kg.attractor.job_search_project.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespondedApplicant {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private boolean confirmation;
}
