package kg.attractor.job_search_project.dto;

import jakarta.validation.constraints.*;
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

        private String companyName;

        @NotBlank(message = "{userDro.validation.name}")
        private String name;

        @NotBlank(message = "{userDro.validation.surname}")
        private String surname;

        @NotNull(message = "{userDro.validation.age}")
        @Min(value = 18, message = "{userDro.validation.ageMin}")
        @Max(value = 90, message = "{userDro.validation.ageMax}")
        private Integer age;

        @Email
        @NotBlank(message = "{userDto.validation.email}")
        private String email;

        @Size(min = 3, max = 15, message = "{userSto.validation.password}")
        private String password;

        @Pattern(regexp = "^996\\d{9}$", message = "{userSto.validation.phoneNumber}")
        private String phoneNumber;

        private String avatar;

        private Boolean enabled = true;

        private Long authorityId;
}
