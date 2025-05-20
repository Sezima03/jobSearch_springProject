package kg.attractor.job_search_project.service.impl;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.job_search_project.dto.UserDto;
import kg.attractor.job_search_project.dto.VacancyDto;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.exceptions.UserNotFoundException;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.model.Vacancy;
import kg.attractor.job_search_project.repository.UserRepository;
import kg.attractor.job_search_project.service.UserService;
import kg.attractor.job_search_project.util.CommonUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;

    private User convertToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setCompanyName(userDto.getCompanyName());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAvatar(userDto.getAvatar());
        user.setEnabled(true);
        user.setAuthorityId(userDto.getAuthorityId());

        return user;
    }

    @Override
    public String registerUser(UserDto userDto){
        log.info("Starting registration for User with Email : {}", userDto.getEmail());

        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = convertToUser(userDto);

        userRepository.save(user);
        log.info("User with Email registered successfully: {}", userDto.getEmail());
        return "успешно";
    }

    @Override
    public List<UserDto> getSearchByName(String name){
        log.info("Searching Users by name : {}", name);
        List<User> users = userRepository.findAllByName(name);

        if (users==null || users.isEmpty()) {
            log.warn("Users not found with name: {}", name);
            throw new JobSearchException("НЕт пользователя с таким именем");
        }
        log.info("Users found : {}", users);
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .build())
                .toList();
    }


    @Override
    public List<UserDto> getSearchByNumber(String number){
        log.info("Searching Users by number : {}", number);
        List<User> users= userRepository.searchByPhoneNumber(number);
        if (users == null || users.isEmpty()) {
            log.warn("Users not found for this phone number: {}", number);
            throw new JobSearchException("Phone Number Not Found");
        }
        log.info("Users found by this number: {}", number);
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .build())
                .toList();
    }

    @Override
    public List<UserDto> getSearchByEmail(String email){
        log.info("Searching Users by email : {}", email);
        List<User> users=userRepository.searchByEmail(email);
        if (users == null || users.isEmpty()) {
            log.warn("Users with email {} not found", email);
            throw new JobSearchException("Email not Found");
        }
        log.info("Users found by email: {}", email);
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .build())
                .toList();
    }
    @Override
    public List<VacancyDto> getRespondedToVacancy(Long applicantId) {
        log.info("Searching Users by applicant id : {}", applicantId);
        List <Vacancy> vacancies = userRepository.findAllRespondedByApplicantId(applicantId);
        if (vacancies==null || vacancies.isEmpty()) {
            log.warn("Vacancies not found for applicant id : {}", applicantId);
            throw new JobSearchException("Vacancy Not Found");
        }
        log.info("Vacancies found : {}", vacancies);
        return vacancies.stream()
                .map(vacancy -> VacancyDto.builder()
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .categoryId(vacancy.getCategoryId())
                        .salary(vacancy.getSalary())
                        .expFrom(vacancy.getExpFrom())
                        .expTo(vacancy.getExpTo())
                        .isActive(vacancy.getIsActive())
                        .authorId(vacancy.getAuthorId())
                        .createdDate(vacancy.getCreatedDate())
                        .updateTime(vacancy.getUpdateTime())
                        .build())
                .toList();
    }

    @Override
    public User getById(Long id){

        return userRepository.findById(id)
                .orElseThrow(() -> new JobSearchException("User not found with id : " + id));
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new JobSearchException("User not found"));
    }

    private void updateResetPasswordToken(String token, String email) {
        User user =userRepository.findByEmail(email)
                .orElseThrow(()->new JobSearchException("User not found"));
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updatePassword(User user, String password){
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }

   @Override
   public void makeResetPasswdLnk(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLnk = CommonUtilities.getSiteUrl(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLnk);
    }

    @Override
    public void updateProfile(UserDto userDto){
        User usr = userRepository.findById(userDto.getId())
                        .orElseThrow(UserNotFoundException::new);
        usr.setCompanyName(userDto.getCompanyName());
        usr.setName(userDto.getName());
        usr.setSurname(userDto.getSurname());
        usr.setAge(userDto.getAge());
        usr.setEmail(userDto.getEmail());
        usr.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(usr);
    }

    @Override
    public User getFindById(Long id){
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
    @Override
    public User getFindUserByName(String name){
        return userRepository.findByName(name)
                .orElseThrow(() -> new JobSearchException("User Not Found"));
    }
}
