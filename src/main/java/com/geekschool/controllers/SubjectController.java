package com.geekschool.controllers;

import com.geekschool.dto.SubjectDto;
import com.geekschool.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/subjects")
public class SubjectController {

    private SubjectService subjectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectDto> getSubjects() {
        return subjectService.getSubjects();
    }
}
