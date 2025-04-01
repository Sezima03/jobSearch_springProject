package kg.attractor.job_search_project.model;
import lombok.*;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private Boolean enabled;
    private Long authorityId;
}
