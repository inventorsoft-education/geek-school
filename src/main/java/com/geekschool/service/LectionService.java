package com.geekschool.service;

import com.geekschool.entity.Lection;
import com.geekschool.entity.User;
import com.geekschool.repository.LectionRepository;
import com.geekschool.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class LectionService {

    private LectionRepository lectionRepository;
    private UserRepository userRepository;

    @Transactional
    public Lection createLection(String name, String description, String username) {
        Lection lection = new Lection();

        User teacher = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        lection.setName(name);
        lection.setDescription(description);
        lection.setTeacher(teacher);

        return lectionRepository.save(lection);
    }

    @Transactional
    public Lection findLectionById(long id) {
        return lectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void updateTeacherById(long id, String username) {
        User teacher = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        lectionRepository.updateTeacherById(id, teacher);
    }

    @Transactional
    public List<Lection> getAllLections() {
        return lectionRepository.findAll();
    }

    @Transactional
    public void deleteLectionById(long id) {
        lectionRepository.deleteById(id);
    }
}
