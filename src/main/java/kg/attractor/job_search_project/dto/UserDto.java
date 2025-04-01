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

        @NotBlank(message = "Имя не должно быть пустым")
        private String name;

        @NotBlank(message = "Фамилия не должно быть пустой")
        private String surname;

        @NotNull(message = "Возраст не должно быть пустой")
        @Min(value = 18, message = "Возраст не должно быть не менее 18 лет")
        private Integer age;

        @Email
        @NotBlank(message = "Email не должно быть пустым")
        private String email;

        @NotBlank(message = "Пароль не должно быть пустым")
        @Size(min = 3, max = 15, message = "lenght must be>5 and <=15")
        private String password;

        @NotBlank(message = "Номер телефона не должен быть пустым")
        private String phoneNumber;

        private String avatar;

        private Boolean enabled;

        @NotNull(message = "Укажите ID")
        private Long authorityId;
}
