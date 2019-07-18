package com.geekschool.controllers;

import com.geekschool.dto.courses.CourseLectionsDto;
import com.geekschool.dto.lections.LectionDateDto;
import com.geekschool.dto.lections.LectionDto;
import com.geekschool.dto.lections.LectionValidDto;
import com.geekschool.mapper.LectionMapper;
import com.geekschool.service.LectionService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("${api}/lections")
public class LectionController {

    private LectionService lectionService;
    private LectionMapper lectionMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LectionDto> getLections() {
        return lectionService.getAllLections()
                .stream()
                .map(lection -> lectionMapper.convertToLectionDto(lection))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LectionDto getLectionById(@PathVariable Long id) {
        return lectionMapper.convertToLectionDto(lectionService.findLectionById(id));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeacherOnLecture(@PathVariable("id") Long id,
                                       @RequestParam("teacher_name") String username) {
       lectionService.updateTeacherById(id, username);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public LectionDto addLection(@Valid @RequestBody LectionValidDto lectionValidDto) {
        return lectionMapper.convertToLectionDto(lectionService.createLection(lectionValidDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateDateLection(@Valid @RequestBody CourseLectionsDto courseLectionsDto) {
        lectionService.updateDateCourse(courseLectionsDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLectionById(@PathVariable Long id) {
        lectionService.deleteLectionById(id);
    }
}
