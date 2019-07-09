package com.geekschool.service.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private long id;
    private String subject;
    private String emailTo;
    private String description;
    private String templateName;
    private Map<String, Object> templateVariables;
}
