package kg.attractor.job_search_project.service.impl;

import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.AuthService;
import org.springframework.stereotype.Service;

@Service //аннотация для указания что это сервис
public class AuthServiceImpl implements AuthService {


    @Override
    public User createAccount(String name, String surname, int age, String email, String password, String phone_number, String account_type){
        //TODO Здесь будет логика создание учетной записи
        //TODO тип пользователя. В зависимости от типа аккаунта задаются параметры
        //TODO проверка на пустую строку
        //TODO Проверяем загружено ли фото. Если фото профиля не загружено установим по умолчанию
        return createAccount(name, surname, age, email, password, phone_number, account_type);
    }
}
