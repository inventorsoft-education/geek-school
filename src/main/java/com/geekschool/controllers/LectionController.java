package com.geekschool.controllers;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.Lection;
import com.geekschool.entity.Subject;
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
                                 @RequestParam String description,
                                 @RequestParam long teacher_id,
                                 @RequestParam long subject_id) {

        Lection lection = new Lection();

        User teacher = userRepository.findById(teacher_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Subject subject = subjectService.findById(subject_id);

        lection.setName(name);
        lection.setDescription(description);
        lection.setTeacher(teacher);
        lection.setCourse(subject);

        return lectionService.createLection(lection);
    }
}
