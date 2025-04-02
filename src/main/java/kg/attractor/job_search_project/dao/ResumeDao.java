package kg.attractor.job_search_project.dao;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.model.EducationInfo;
import kg.attractor.job_search_project.model.Resume;
import kg.attractor.job_search_project.model.WorkExperienceInfo;
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

    public List<Resume> getResume() {
        String sql = "select * from resume";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public void getUpdateResume(Long resumeId, Resume resume){
        String sql ="select count(*) from resume where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class,resumeId);
        if(count<=0){
            throw new JobSearchException("Resume not found");
        }
        String sql2 = "update resume set " +
                "applicant_id = ?, " +
                "name = ?, " +
                "category_id = ?, " +
                "salary = ?, " +
                "is_active = ? " +
                "where id = ?";

        jdbcTemplate.update(sql2, resume.getApplicantId(), resume.getName(), resume.getCategoryId(), resume.getSalary(), resume.isActive(), resumeId);
    }


    public boolean deleteResume(Long resumeId) {
        String sql = "select count(*) from resume where id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, resumeId);

        if (count > 0) {

            String deleteEduInfoSql = "DELETE FROM education_info WHERE resume_id = ?";
            jdbcTemplate.update(deleteEduInfoSql, resumeId);

            String deleteWorkExperienceSql = "DELETE FROM work_experience_info WHERE resume_id = ?";
            jdbcTemplate.update(deleteWorkExperienceSql, resumeId);

            String sql2 = "delete from responded_applicant where id = ?";
            jdbcTemplate.update(sql2, resumeId);

            String sql3 = "delete from resume where id = ?";
            int res = jdbcTemplate.update(sql3, resumeId);

            return res > 0;
        }else {
            throw new JobSearchException("Resume not found");
        }
    }

    public void getCreateResume(Resume resume) {
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

    public List<Resume> getAllResumeByCategory(String category){
        String sql = "select r.* " +
                "from resume r " +
                "where r.CATEGORY_ID = ( " +
                "select c.id " +
                "from CATEGORIES c " +
                "where c.NAME = ?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category);
    }

    public List<Resume> getAllResumeByApplicantId(Long applicantId){
        String sql = "select * from resume where applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), applicantId);
    }

    public void getCreateEduInfo(EducationInfo educationInfo){
        String sql = "insert into education_info (" +
                "RESUME_ID, " +
                "institution, " +
                "program, " +
                "start_date, " +
                "end_date, " +
                "degree)" +
                "values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, educationInfo.getResumeId(), educationInfo.getInstitution(), educationInfo.getProgram(), educationInfo.getStartDate(), educationInfo.getEndDate(), educationInfo.getDegree());
    }

    public void getCreateWorkExperienceInfo(WorkExperienceInfo workExperienceInfo){
        String sql = "insert into work_experience_info (resume_id, years, company_name, position, responsibilites)" +
                "values (?,?,?,?,?)";

        jdbcTemplate.update(sql, workExperienceInfo.getResumeId(), workExperienceInfo.getYear(), workExperienceInfo.getCompanyName(), workExperienceInfo.getPosition(), workExperienceInfo.getResponsibilities());
    }
}
