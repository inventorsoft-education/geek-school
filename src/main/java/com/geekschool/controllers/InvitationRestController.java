package com.geekschool.controllers;

import com.geekschool.dto.InvitationValidateDto;
import com.geekschool.entity.InvitedToken;
import com.geekschool.repository.InvitedTokenRepository;
import com.geekschool.service.ForgotPasswordService;
import com.geekschool.service.InvitationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class InvitationRestController {

    private InvitationService invitationService;
    private InvitedTokenRepository invitedTokenRepository;
    private ForgotPasswordService forgotPasswordService;


    @GetMapping(value = "/invitation")
    public void sendInvitation(@Valid InvitationValidateDto invitationValidateDto){
        invitationService.sendInvitation(invitationValidateDto.getFormType(),invitationValidateDto.getEmail());
    }

    //@GetMapping(value = "/invitation/user/token")
    @PostMapping(value = "/invitation/user/token")
    public Long getInformationByToken(@RequestParam String token){
        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        if(forgotPasswordService.checkToken(invitedToken)){
            return invitedToken.getUser().getId();
        }
        return null;
    }



}
