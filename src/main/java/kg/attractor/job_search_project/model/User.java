package kg.attractor.job_search_project.model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String avatar;
    private Boolean enabled = true;
    private String resetPasswordToken;

    @Column(name = "authority_id")
    private Long authorityId;
}
