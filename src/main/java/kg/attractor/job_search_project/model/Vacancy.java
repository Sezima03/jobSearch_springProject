package kg.attractor.job_search_project.model;
import java.sql.Timestamp;

public class Vacancy {
    private int id;
    private String name;
    private String description;
    private int category_id;
    private Double salary;
    private int exp_from;
    private int exp_to;
    private boolean isActive;
    private int author_id;
    private Timestamp created_date;
    private Timestamp update_time;
}
