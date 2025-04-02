package kg.attractor.job_search_project.dao;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.RespondedApplicant;
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

    public List<Vacancy> getVacancies() {
        String sql = "select * from VACANCYUSR";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public void getCreateVacancy(Vacancy vacancy) {
        String sql = "insert into vacancyusr(name, description, category_id, salary, exp_from, exp_to, is_active, author_id) " +
                "values(?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(), vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.getIsActive(), vacancy.getAuthorId());
    }

    public void getUpdateVacancy(Vacancy vacancy, Long id) {

        String sql = "select COUNT(*) from vacancyusr where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if (count > 0) {
            String sql2 = "update vacancyusr set " +
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
            jdbcTemplate.update(sql2, vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(),
                    vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(),
                    vacancy.getIsActive(), vacancy.getAuthorId(),
                    vacancy.getCreatedDate(), vacancy.getUpdateTime(), id);
        }else {
            throw new JobSearchException("Vacancy with ID " + id + " not found.");
        }
    }

    public List<Vacancy> getVacancyByCategory(Long category_id){
        String sql = "select v.id, v.name, v.description, v.category_id, v.salary, v.exp_from, v.exp_to, v.is_active, v.author_id, v.created_date, v.update_time, c.id AS category_id, c.name AS category_name " +
                "from vacancyusr v " +
                "JOIN categories c on v.category_id = c.id " +
                "where c.id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), category_id);
    }

    public boolean getDeleteVacancy(Long vacancyId) {
        String sqlCheck = "select COUNT(*) from responded_applicant where vacancy_id = ?";
        int countResponded = jdbcTemplate.queryForObject(sqlCheck, Integer.class, vacancyId);

        if (countResponded > 0) {
            String deleteRespondedSql = "delete from responded_applicant where vacancy_id = ?";
            jdbcTemplate.update(deleteRespondedSql, vacancyId);
        }
        String sql = "select COUNT(*) from vacancyusr where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, vacancyId);

        if (count > 0) {
            String deleteVacancySql = "delete from vacancyusr where id = ?";
            int res = jdbcTemplate.update(deleteVacancySql, vacancyId);
            return res > 0;
        }
        throw new JobSearchException("Vacancy with ID " + vacancyId + " not found.");
    }


    public List<RespondedApplicant> getFindRespondedApplicantByVacancyId(Long vacancyId) {
        String sql = "select * from responded_applicant where id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class), vacancyId);
    }
    public List<Vacancy> getAllActiveVacancies() {
        String sql = "select * from vacancyusr where IS_ACTIVE = true";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }
}
