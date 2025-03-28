package kg.attractor.job_search_project.dao;
import kg.attractor.job_search_project.model.UserImage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserImageDao {

    private final JdbcTemplate  jdbcTemplate;

    public Optional<UserImage> getImgById(Long id){
        String sql = "select * from user_images where id=?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new BeanPropertyRowMapper<>(UserImage.class),
                                id
                        )
                )
        );
    }

    public void save(Long userId,String fileName ){
        String sql = "insert into user_images (user_id,file_name) values(?,?)";
        jdbcTemplate.update(sql,userId,fileName);
    }
}
