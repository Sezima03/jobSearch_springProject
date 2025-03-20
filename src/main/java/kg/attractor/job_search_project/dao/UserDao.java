package kg.attractor.job_search_project.dao;

import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        String sql="insert into users(name, surname, age, email, password, phone_number, avatar, account_type) values(?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getAge(), user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getAvatar(), user.getAccountType());
    }

    public List<Resume> searchByCategory(String category){
        String sql = "SELECT r.* FROM resume r " +
                "JOIN categories c ON r.category_id = c.id " +
                "WHERE c.name = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category);
    }
}
