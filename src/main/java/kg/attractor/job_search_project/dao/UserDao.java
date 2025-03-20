package kg.attractor.job_search_project.dao;

import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.model.User;
import lombok.RequiredArgsConstructor;
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
}
