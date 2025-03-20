package kg.attractor.job_search_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

        private Long id;
        private String name;
        private String surname;
        private int age;
        private String email;
        private String password;
        private String phoneNumber;
        private String avatar;
        private String accountType;
}
