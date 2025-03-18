package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.model.User;

public interface AuthService {

    User createAccount(String name, String surname, int age, String email, String password, String phone_number, String account_type);
}
