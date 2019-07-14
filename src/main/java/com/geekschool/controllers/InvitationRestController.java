package com.geekschool.controllers;

import com.geekschool.dto.InvitationValidateDto;
import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.Role;
import com.geekschool.entity.User;
import com.geekschool.repository.InvitedTokenRepository;
import com.geekschool.service.ForgotPasswordService;
import com.geekschool.service.InvitationService;
import com.geekschool.service.ValidationService;
import com.geekschool.service.mail.MailSenderService;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class InvitationRestController {

    private InvitationService invitationService;
    private InvitedTokenRepository invitedTokenRepository;
    private ForgotPasswordService forgotPasswordService;
    private ValidationService validationService;
    private Map<String,String> errorsArray;


    @GetMapping(value = "/invitation")
    public Map<String,String> sendInvitation(@Valid InvitationValidateDto invitationValidateDto, BindingResult result){

        errorsArray.clear();
        errorsArray = validationService.checkErrors(result);

        if (errorsArray.containsKey("success")){
            invitationService.sendInvitation(invitationValidateDto.getFormType(),invitationValidateDto.getEmail());
        }

        return errorsArray;
    }

    @GetMapping(value = "/invitation/user/token")
    public Long getInformationByToken(@RequestParam String token){
        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        if(forgotPasswordService.checkToken(invitedToken)){
            return invitedToken.getUser().getId();
        }
        return null;
    }



}
