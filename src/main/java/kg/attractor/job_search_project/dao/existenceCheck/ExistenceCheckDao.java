package kg.attractor.job_search_project.dao.existenceCheck;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistenceCheckDao {

    private final JdbcTemplate jdbcTemplate;

    public boolean existsByAuthorId(Long authorId) {
        String sql = "select count(*) from users where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, authorId);
        return count > 0;
    }

    public boolean existaCategoryId(Long categoryId) {
        String sql = "select count(*) from categories where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, categoryId);
        return count > 0;
    }
}
