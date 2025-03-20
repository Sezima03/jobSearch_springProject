package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getSearchByName(String name);

    List<UserDto> getSearchByNumber(String number);
}
