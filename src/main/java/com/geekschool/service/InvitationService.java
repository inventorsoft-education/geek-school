package com.geekschool.service;

import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.User;
import com.geekschool.repository.InvitedTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import static java.lang.Integer.valueOf;

@Service
public class InvitationService {

    @Autowired
    private InvitedTokenRepository invitedTokenRepository;

    @Value("${invitation.default.link}")
    private String timeForLink;

    public void createInvitedToken(UUID uuid, User user){
        InvitedToken invitedToken = new InvitedToken();
        invitedToken.setDateOff(LocalDate.now().plusDays(valueOf(timeForLink)));
        invitedToken.setToken(uuid.toString());
        invitedToken.setUser(user);
        invitedTokenRepository.save(invitedToken);
    }
}
