package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.dao.UserDao;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;

    @Override
    public String registerUser(User user){

        if (userDao.isEmailTaken(user.getEmail())!=null){
            return "Email уже существует";
        }
        userDao.saveUser(user);
        return "успешно";
    }
}
