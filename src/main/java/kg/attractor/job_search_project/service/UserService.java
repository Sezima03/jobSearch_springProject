package kg.attractor.job_search_project.service;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {


    String registerUser(UserDto userDto);

    List<UserDto> getSearchByName(String name);

    List<UserDto> getSearchByNumber(String number);

    List<UserDto> getSearchByEmail(String email);

    List<VacancyDto> getRespondedToVacancy(Long applicantId);

    User getById(Long id);

    User findUserByUsername(String username);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String password);

    void makeResetPasswdLnk(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;

    void updateProfile(UserDto userDto);
}
