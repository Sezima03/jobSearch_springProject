package kg.attractor.job_search_project.controller;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.job_search_project.exceptions.JobSearchException;
import kg.attractor.job_search_project.exceptions.UserNotFoundException;
import kg.attractor.job_search_project.model.User;
import kg.attractor.job_search_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "registrations/login";
    }

    @GetMapping("forgot_password")
    public String showForgotPassword() {
        return "passTemp/forgot_password_form";
    }

    @PostMapping("forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            String token = userService.makeResetPasswdLnk(request);
            model.addAttribute("message", "Скопируйте токен и введите его ниже:");
            model.addAttribute("token", token);
        } catch (UserNotFoundException | JobSearchException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "passTemp/forgot_password_form";
    }

    @GetMapping("enter_token")
    public String showEnterTokenForm() {
        return "passTemp/enter_token_form";
    }
    @PostMapping("enter_token")
    public String processEnterToken(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
            return "passTemp/reset_password_form";
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Неверный токен");
            return "passTemp/enter_token_form";
        }
    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        }catch (UserNotFoundException e){
            model.addAttribute("error", "invalid token");
        }
        return "passTemp/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        try {
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("message", "Invalid Token");
        }
        return "passTemp/passMessage";
    }
}