package com.geekschool.controllers;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.Lection;
import com.geekschool.entity.Course;
import com.geekschool.entity.User;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.LectionService;
import com.geekschool.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/lection")
public class LectionController {

    private LectionService lectionService;
    private SubjectService subjectService;
    private UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LectionDto> getLections() {
        return lectionService.getAllLections();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LectionDto getLectionById(@PathVariable long id) {
        return lectionService.findLectionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LectionDto addLection(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("teacher_name") String username,
                                 @RequestParam("subject_name") String subjectName) {

        Lection lection = new Lection();

        User teacher = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Course course = subjectService.findByName(subjectName);

        lection.setName(name);
        lection.setDescription(description);
        lection.setTeacher(teacher);
        lection.setCourse(course);

        return lectionService.createLection(lection);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeacherOnLecture(@PathVariable("id") long id,
                                       @RequestParam("teacher_name") String username) {
        User teacher = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        lectionService.updateLectionById(id, teacher);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLectionById(@PathVariable long id) {
        lectionService.deleteLectionById(id);
    }
}
