package com.geekschool.service.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailSenderService {

    private JavaMailSender javaMailSender;

    // TODO: 2019-07-05 externalize email template. Configure thymeleaf or fremarker engine to process email templates
    public void sendMessage(String email, String link) {
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(email);
            message.setSubject("Invitation to InventorSoft");
            message.setText("<b>This your <a href='" + link + "'>link</a></b>", true);
        });

    }

}
