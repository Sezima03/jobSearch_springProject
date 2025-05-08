package kg.attractor.job_search_project.config;
import kg.attractor.job_search_project.service.impl.CustomerAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomerAuthSuccessHandler customerAuthSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .successHandler(customerAuthSuccessHandler)
                        .failureUrl("/auth/login?error=true")
                        .permitAll())

                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/",
                                "/register/employer",
                                "/register/applicant",
                                "/info/{id}").permitAll()
                        .requestMatchers(
                                "/resume/update/{id}",
                                "/resume/create",
                                "/resume/editResume/{resumeId}",
                                "/users/profileApplicant").hasAuthority("APPLICANT")
                        .requestMatchers(
                                "/users/profileEmp",
                                "/created",
                                "/resume/info/{id}",
                                "/edit/{vacancyId}").hasAuthority("EMPLOYER")
                        .anyRequest().permitAll());

        return http.build();
    }
}
