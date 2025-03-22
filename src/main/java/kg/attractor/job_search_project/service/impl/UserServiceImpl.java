package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public String registerUser(User user){

        if (userDao.isEmailTaken(user.getEmail())!=null){
            return "Email уже существует";
        }
        userDao.saveUser(user);
        return "успешно";
    }

    @Override
    public List<UserDto> getSearchByName(String name){
        List<User> users= userDao.getSearchByName(name);

        if (users != null && !users.isEmpty()) {
            return users.stream()
                    .map(user -> UserDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .phoneNumber(user.getPhoneNumber())
                            .avatar(user.getAvatar())
                            .accountType(user.getAccountType())
                            .build())
                    .toList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<UserDto> getSearchByNumber(String number){
        List<User> users= userDao.getSearchByNumber(number);
        if (users != null && !users.isEmpty()) {
            return users.stream()
                    .map(user -> UserDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .phoneNumber(user.getPhoneNumber())
                            .avatar(user.getAvatar())
                            .accountType(user.getAccountType())
                            .build())
                    .toList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<UserDto> getSearchByEmail(String email){
        List<User> users=userDao.getSearchByEmail(email);
        if (users != null && !users.isEmpty()) {
            return users.stream()
                    .map(user -> UserDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .phoneNumber(user.getPhoneNumber())
                            .avatar(user.getAvatar())
                            .accountType(user.getAccountType())
                            .build())
                    .toList();
        }
        return Collections.emptyList();
    }
    @Override
    public List<VacancyDto> getRespondedToVacancy(Long applicantId) {
        List <Vacancy> vacancies = userDao.responseToVacancies(applicantId);
        if (!vacancies.isEmpty()) {
            return vacancies.stream()
                    .map(vacancy -> VacancyDto.builder()
                            .id(vacancy.getId())
                            .name(vacancy.getName())
                            .description(vacancy.getDescription())
                            .salary(vacancy.getSalary())
                            .expFrom(vacancy.getExpFrom())
                            .expTo(vacancy.getExpTo())
                            .isActive(vacancy.isActive())
                            .authorId(vacancy.getAuthorId())
                            .createdDate(vacancy.getCreatedDate())
                            .updateTime(vacancy.getUpdateTime())
                            .build())
                    .toList();
        }
        return List.of();
    }
}
