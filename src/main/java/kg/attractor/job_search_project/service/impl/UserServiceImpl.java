package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.model.User;
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
//        return userDao.getSearchByEmail(email);
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
}
