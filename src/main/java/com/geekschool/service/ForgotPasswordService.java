package com.geekschool.service;

import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.User;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.mail.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ForgotPasswordService {


    private String host;
    private UserRepository userRepository;
    private MailSenderService mailSenderService;
    private InvitationService invitationService;

    public ForgotPasswordService(final UserRepository userRepository,
                             final MailSenderService mailSenderService,
                             final InvitationService invitationService,
                             @Value("${invitation.default.host}") final String host) {
        this.userRepository = userRepository;
        this.mailSenderService = mailSenderService;
        this.invitationService = invitationService;
        this.host = host;
    }

    private String createForgotPasswordLink(UUID uuid){
        return host+"user/password/"+uuid;
    }

    public Boolean checkToken(InvitedToken invitedToken){
        return LocalDate.now().isBefore(invitedToken.getDateOff());
    }

    public void sendRestorePasswordLink(String login){
        UUID uuid = UUID.randomUUID();
        String link = createForgotPasswordLink(uuid);
        User user = userRepository.findByUsername(login).orElseThrow();
        invitationService.createInvitedToken(uuid, user);
        mailSenderService.sendMessage(user.getEmail(), link, "email/forgot-password");
    }
}
