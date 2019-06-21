package com.geekschool.mapper;

import com.geekschool.dto.MessageDto;
import com.geekschool.entity.Message;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessageMapper {

    private Message message;

    public MessageDto convertToMessageDto() {
        return new MessageDto(
                message.getId(),
                message.getSubject(),
                message.getEmailTo(),
                message.getDescription());
    }
}
