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
        @Max(value = 90, message = "Возраст не должен превышать 90 лет")
        private Integer age;

        @Email
        @NotBlank(message = "Email не должно быть пустым")
        private String email;

        @NotBlank(message = "Пароль не должно быть пустым")
        @Size(min = 3, max = 15, message = "lenght must be>3 and <=15")
        private String password;

        @NotBlank(message = "Номер телефона не должен быть пустым")
        @Pattern(regexp = "^996\\d{9}$", message = "Неверный формат номера телефона")
        private String phoneNumber;

        private String avatar;

        private Boolean enabled = true;

        @NotNull(message = "Укажите ID  1-Соискатель, 2-Работодатель")
        @Min(value = 1, message = "ID не может быть меньше 1")
        @Max(value = 2, message = "ID не может быть больше 2")
        private Long authorityId;
}
