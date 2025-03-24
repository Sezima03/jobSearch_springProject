package kg.attractor.job_search_project.dao;

import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {

    private final JdbcTemplate jdbcTemplate;

    //TODO реализован
    public Optional<Resume> findResumeById(Long id) {
        String sql = "select * from resume where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new BeanPropertyRowMapper<>(Resume.class),
                                id)
                )
        );
    }
    //TODO реализован
    public List<Resume> getResume() {
        String sql = "select * from resume";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }
    //TODO реализован
    public void getUpdateResume(Long resumeId, Resume resume){
        String sql = "update resume set " +
                "applicant_id = ?, " +
                "name = ?, " +
                "category_id = ?, " +
                "salary = ?, " +
                "is_active = ?, " +
                "created_date = ?, " +
                "update_time = ? " +
                "where id = ?";

        jdbcTemplate.update(sql, resume.getApplicantId(), resume.getName(), resume.getCategoryId(), resume.getSalary(), resume.isActive(), resume.getCreatedDate(), resume.getUpdateTime(), resumeId);
    }

    public void insertResume(Resume resume) {
        //TODO Добавления резюме
        //TODO при создании нового резюме учтите что модули образование и Опыт работы это отдельные таблицы
    }

    //TODO реализован
    public boolean deleteResume(Long resumeId) {
        String sql = "select count(*) from resume where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, resumeId);

        if (count > 0) {

            String sql2 = "delete from responded_applicant where id = ?";
            jdbcTemplate.update(sql2, resumeId);

            String sql3 = "delete from resume where id = ?";
            int res = jdbcTemplate.update(sql3, resumeId);

            return res > 0;
        }
        return false;
    }

    public void getCreateResume(ResumeDto resume){
        String sql="insert into resume(applicant_id, name, category_id, salary, is_active, created_date, update_time)" +
                "values(?,?,?,?,?,?,?)";

        jdbcTemplate.update(
                sql,
                resume.getApplicantId(),
                resume.getName(),
                resume.getCategoryId(),
                resume.getSalary(),
                resume.isActive(),
                resume.getCreatedDate(),
                resume.getUpdateTime());
    }
}
