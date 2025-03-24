package kg.attractor.job_search_project.dao;

import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {

    private final JdbcTemplate jdbcTemplate;

    //TODO реализована
    public List<Vacancy> getVacancies() {
        String sql = "select * from VACANCYUSR";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }
    //TODO реализована
    public void getCreateVacancy(Vacancy vacancy) {
        String sql = "insert into vacancyusr(name, description, category_id, salary, exp_from, exp_to, is_active, author_id) " +
                "values(?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(), vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.isActive(), vacancy.getAuthorId());
    }
//TODO работает
    public void getUpdateVacancy(VacancyDto vacancy, Long id) {
        String sql = "update vacancyusr set " +
                "name = ?, " +
                "description = ?, " +
                "category_id = ?, " +
                "salary = ?, " +
                "exp_from = ?, " +
                "exp_to = ?, " +
                "is_active = ?, " +
                "author_id = ?," +
                "created_date = ?, " +
                "update_time = ?" +
                "where id = ?";
        jdbcTemplate.update(sql, vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(),
                vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(),
                vacancy.isActive(), vacancy.getAuthorId(),
                vacancy.getCreatedDate(), vacancy.getUpdateTime(), id);
    }

    //TODO работает
    public boolean getDeleteVacancy(Long vacancyId) {
        String sql = "select COUNT(*) from vacancyusr where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, vacancyId);

        if (count > 0) {
            String sql2= "delete from vacancyusr where id = ?";
            int res = jdbcTemplate.update(sql2, vacancyId);

            return res>0;
        }
        return false;
    }

    public void unpublish(Long vacancyId) {
        //TODO метод для снятия вакансии с публикации
    }
}
