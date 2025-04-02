package kg.attractor.job_search_project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DataSource dataSource;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String fetchUser = """
                select email, password, enabled 
                from USERS 
                where email = ?
                """;

        String fetchRoles = """
                SELECT u.email, a.AUTHORITY
                FROM users u
                JOIN authority a ON u.authority_id = a.id
                WHERE u.email = ?
                """;

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(fetchUser)
                .passwordEncoder(bCryptPasswordEncoder)
                .authoritiesByUsernameQuery(fetchRoles);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/users/register", "vacancy", "images").permitAll()
                        .requestMatchers("resume/add", "vacancy/add").authenticated()
                        .requestMatchers("/resume/update/**", "/resume/delete/**").hasAuthority("APPLICANT")
                        .requestMatchers("/vacancy/update/**", "/vacancy/delete/**").hasAuthority("EMPLOYER")
                        .anyRequest().authenticated());

        return http.build();
    }

}
