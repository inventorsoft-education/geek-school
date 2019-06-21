package com.geekschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
    private long id;
    private String subject;
    private String emailTo;
    private String description;
}
