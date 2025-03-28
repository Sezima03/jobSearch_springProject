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
        @Size(min = 5, max = 15, message = "lenght must be>5 and <=15")
        @Pattern(
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-z]).+$",
                message = "Пароль должен содержать хотя бы одну цифру, одну строчную и одну заглавную букву"
        )
        private String password;

        @NotBlank(message = "Номер телефона не должен быть пустым")
        @Pattern(
                regexp = "^\\+?[1-9]\\d{1,12}$",
                message = "Неверный формат номера телефона"
        )
        private String phoneNumber;

        private String avatar;

        @NotBlank(message = "Выберите тип аккаунта")
        @Pattern(
                regexp = "^(applicant|employer)$",
                message = "Тип аккаунта должен быть 'applicant' или 'employer'"
        )
        private String accountType;
}
