package com.geekschool.mapper;

import com.geekschool.dto.MessageDto;
import com.geekschool.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageDto convertToMessageDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getSubject(),
                message.getEmailTo(),
                message.getDescription());
    }
}
