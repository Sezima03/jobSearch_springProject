package kg.attractor.job_search_project.dao;

import kg.attractor.job_search_project.dto.ResumeDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.RespondedApplicant;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
        String sql="insert into users(name, surname, age, email, password, phone_number, avatar, account_type) values(?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getAge(), user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getAvatar(), user.getAccountType());
    }

    public List<Resume> searchByCategory(String category){
        String sql = "select r.* from resume r " +
                "JOIN categories c ON r.category_id = c.id " +
                "WHERE c.name = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category);
    }

    public List<Resume> getUserById(Long userId) {
        String sql = "SELECT * from resume WHERE applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public List<Vacancy> responseToVacancies(Long applicantId) {
        String sql = "select vacancyusr.id, vacancyusr.name, vacancyusr.description, vacancyusr.salary, vacancyusr.exp_from, vacancyusr.exp_to, vacancyusr.is_active, vacancyusr.author_id, vacancyusr.created_date, vacancyusr.update_time " +
                "from vacancyusr " +
                "JOIN responded_applicant ra on vacancyusr.id = ra.vacancy_id " +
                "JOIN resume r ON r.id = ra.resume_id " +
                "where r.applicant_id = ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), applicantId);
    }

    public List<Vacancy> getVacancy(){
        String sql = "select * from vacancyusr";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacancyByCategory(Long category_id){
        String sql = "select v.id, v.name, v.description, v.category_id, v.salary, v.exp_from, v.exp_to, v.is_active, v.author_id, v.created_date, v.update_time, c.id AS category_id, c.name AS category_name " +
                "from vacancyusr v " +
                "JOIN categories c on v.category_id = c.id " +
                "where c.id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), category_id);
    }

    public List<RespondedApplicant> responseApplicantToVacancy(Long vacancyId){
        String sql ="SELECT id, resume_id, vacancy_id, confirmation " +
                "FROM responded_applicant " +
                "WHERE vacancy_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class), vacancyId);
    }

    public void getCreateResume(ResumeDto resume){
        String sql="insert into resume(applicant_id, name, category_id, salary, is_active, created_date, update_time)" +
                "values(?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, resume.getApplicantId(), resume.getName(), resume.getCategoryId(), resume.getSalary(), resume.isActive(), resume.getCreatedDate(), resume.getUpdateTime());

    }

    public void getCreatedVacancy(VacancyDto vacancy){
        String sql ="insert into vacancyusr(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)" +
                "values(?,?,?,?,?,?,?,?,?, ?)";

        jdbcTemplate.update(sql, vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(), vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.isActive(), vacancy.getAuthorId(), vacancy.getCreatedDate(), vacancy.getUpdateTime());
    }

    public void getupdateResume(Long resumeId, ResumeDto resumedto){
        String sql = "update resume set " +
                "applicant_id=?, name=?, category_id=?, salary=?, is_active=?, created_date=?, update_time=? " +
                "where id=?";

        jdbcTemplate.update(sql, resumedto.getApplicantId(), resumedto.getName(), resumedto.getCategoryId(), resumedto.getSalary(), resumedto.isActive(), resumedto.getCreatedDate(), resumedto.getUpdateTime(), resumeId);
    }

    public boolean getdeleteResume(Long resumeId){
        String sql = "select count(*) from resume where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, resumeId);

        if(count > 0){
            String sql2 ="delete from responded_applicant where resume_id = ?";
            jdbcTemplate.update(sql2, resumeId);

            String sql3 ="delete from resume where id = ?";
            int res=jdbcTemplate.update(sql3, resumeId);

            return res>0;
        }
        return false;
    }

    public List<Resume> getResume(){
        String sql = "select * from resume";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));

    }

}
