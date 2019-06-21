package com.geekschool.mapper;

import com.geekschool.dto.MessageDto;
import com.geekschool.entity.Message;

public class MessageDtoFactory {

    public static MessageDto convertToUserDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getSubject(),
                message.getEmailTo(),
                message.getDescription());
    }
}
