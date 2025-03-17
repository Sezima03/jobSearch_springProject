package kg.attractor.job_search_project.model;
import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    private String phone_number;
    private String avatar;
    private String account_type;
}
