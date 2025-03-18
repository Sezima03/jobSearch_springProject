package kg.attractor.job_search_project.model;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Resume {
    private int id;
    private int applicant_id;
    private String name;
    private int category_id;
    private Double salary;
    private boolean isActive;
    private Timestamp created_date;
    private Timestamp update_time;

}
