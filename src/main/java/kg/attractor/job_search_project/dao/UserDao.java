package kg.attractor.job_search_project.dao;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getSearchByName(String name) {
        String sql = "select * from users where name like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name);
    }

    public List<User> getSearchByNumber(String phone_number) {
        String sql = "select * from users where phone_number like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), phone_number);
    }

    public List<User> getSearchByEmail(String email) {
        String sql = "select * from users where email like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email);
    }

    public User isEmailTaken(String email) {
        String sql = "select * from users where email = ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void saveUser(User user){
        String sql="insert into users(name, surname, age, email, password, phone_number, avatar, enabled, authority_id) " +
                "values(?,?,?,?,?,?,?,?,?)";

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getAge(), user.getEmail(), encodedPassword, user.getPhoneNumber(), user.getAvatar(), user.getEnabled(), user.getAuthorityId());
    }

    public List<Vacancy> responseToVacancies(Long applicantId) {

        String sql = "select * from vacancyusr v " +
                "where v.id in(select ra.vacancy_id " +
                "from responded_applicant ra " +
                "JOIN resume r ON r.id = ra.resume_id " +
                "where applicant_id = ?)";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), applicantId);
    }

}