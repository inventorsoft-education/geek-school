package com.geekschool.controllers;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.Lection;
import com.geekschool.entity.User;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.LectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
// TODO: 2019-07-05 use plural form for collection url (prosto esky dobavyty)
@RequestMapping("api/lection")
public class LectionController {

    private LectionService lectionService;
    private UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LectionDto> getLections() {
        return lectionService.getAllLections();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LectionDto getLectionById(@PathVariable Long id) {
        return lectionService.findLectionById(id);
    }

    // TODO: 2019-07-05 move logic into service layer
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LectionDto addLection(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("teacher_name") String username) {

        Lection lection = new Lection();

        User teacher = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        lection.setName(name);
        lection.setDescription(description);
        lection.setTeacher(teacher);

        return lectionService.createLection(lection);
    }

    // TODO: 2019-07-05 move logic into service layer
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeacherOnLecture(@PathVariable("id") Long id,
                                       @RequestParam("teacher_name") String username) {
        User teacher = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        lectionService.updateLectionById(id, teacher);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLectionById(@PathVariable Long id) {
        lectionService.deleteLectionById(id);
    }
}
