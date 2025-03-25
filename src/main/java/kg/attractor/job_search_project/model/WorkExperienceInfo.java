package kg.attractor.job_search_project.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkExperienceInfo {
    private Long id;
    private Long resumeId;
    private int year;
    private String companyName;
    private String position;
    private String responsibilities;
}
