package com.geekschool.service;

import com.geekschool.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }

    public void send(MessageDto message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(message.getEmailTo());
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getDescription());

        javaMailSender.send(mailMessage);
    }

    public void sendMessage(String email, String link){
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(email);
            message.setSubject("Invitation to InventorSoft");
            message.setText("<b>This your <a href='"+link+"'>link</a></b>", true);
        });

    }

}
