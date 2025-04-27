package kg.attractor.job_search_project.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    public void sendEmail(String to, String lnk) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(EMAIL_FROM, "Movie Review Support");
        helper.setTo(to);

        String subject = "Here is the link to reset your password";
        String content = "<p>Hello, </p>+" +
                "<p>You have requested to reset your password</p>" +
                "<p>Click on the link bellow to reset your password.</p>" +
                "<p><a href=\"" + lnk + "\">Change my password</a></p>" +
                "<br>" +
                "<p>Ignore this email if you do remember your password, " +
                "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(message);
    }
}
