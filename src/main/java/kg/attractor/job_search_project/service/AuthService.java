package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.model.User;

public interface AuthService {
    String registerUser(User user);
}
