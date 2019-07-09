package com.geekschool.service.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@AllArgsConstructor
@Service
public class MailSenderService {

    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;

    public void sendMessage(String email, String link, String template) {
    final Context context = new Context();
        context.setVariable("link", link);
        String body = templateEngine.process(template, context);
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(email);
            message.setSubject("InventorSoft");
            message.setText(body, true);
        });

    }
}
