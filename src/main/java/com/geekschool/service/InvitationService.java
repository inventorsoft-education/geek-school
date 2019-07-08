package com.geekschool.service;

import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.Role;
import com.geekschool.entity.User;
import com.geekschool.repository.InvitedTokenRepository;
import com.geekschool.service.mail.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import static java.lang.Integer.valueOf;

@Service
public class InvitationService {


    private String host;
    private InvitedTokenRepository invitedTokenRepository;
    private String timeForLink;
    private UserService userService;
    private MailSenderService mailSenderService;

    public InvitationService(final InvitedTokenRepository invitedTokenRepository,
                             @Value("${invitation.default.link}") final String timeForLink,
                             @Value("${invitation.default.host}") final String host,
                             UserService userService,
                             MailSenderService mailSenderService) {
        this.invitedTokenRepository = invitedTokenRepository;
        this.timeForLink = timeForLink;
        this.host = host;
        this.userService = userService;
        this.mailSenderService = mailSenderService;
    }

    private String createTokenLink(UUID uuid){
        return host+"user/"+uuid;
    }

    public void createInvitedToken(UUID uuid, User user){
        InvitedToken invitedToken = new InvitedToken();
        invitedToken.setDateOff(LocalDate.now().plusDays(valueOf(timeForLink)));
        invitedToken.setToken(uuid.toString());
        invitedToken.setUser(user);
        invitedTokenRepository.save(invitedToken);
    }

    public void sendInvitation(Role formType, String email){
        UUID uuid = UUID.randomUUID();
        String link = this.createTokenLink(uuid);
        User user = userService.createUserByToken(formType, email);
        this.createInvitedToken(uuid, user);
        mailSenderService.sendMessage(email, link, "email/invitation");
    }
}
