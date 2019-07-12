package com.geekschool.controllers;

import com.geekschool.dto.LectionDto;
import com.geekschool.mapper.LectionMapper;
import com.geekschool.service.LectionService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LectionDto addLection(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("teacher_name") String username) {
        return lectionMapper.convertToLectionDto(lectionService.createLection(name, description, username));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeacherOnLecture(@PathVariable("id") Long id,
                                       @RequestParam("teacher_name") String username) {
       lectionService.updateTeacherById(id, username);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateDateLection(@RequestParam("id_lection") Long id,
                                 @RequestParam("creation_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDate,
                                 @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endDate) {
        lectionService.updateDateCourse(id, startDate, endDate);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLectionById(@PathVariable Long id) {
        lectionService.deleteLectionById(id);
    }
}
