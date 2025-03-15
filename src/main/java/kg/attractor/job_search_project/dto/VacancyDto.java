package kg.attractor.job_search_project.dto;

import lombok.Data;

@Data
public class VacancyDto {
    private String title;
    private String category;
    private Double salary;
    private String description;
    private int experienceFrom;
    private int experienceTo;
    private boolean active;

}
