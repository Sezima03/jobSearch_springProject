package kg.attractor.job_search_project.service;

import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.model.User;

import java.util.List;

public interface UserService {


    String registerUser(UserDto userDto);

    List<UserDto> getSearchByName(String name);

    List<UserDto> getSearchByNumber(String number);

    List<UserDto> getSearchByEmail(String email);

    List<VacancyDto> getRespondedToVacancy(Long applicantId);
}
