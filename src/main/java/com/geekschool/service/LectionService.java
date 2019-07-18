package com.geekschool.service;

import com.geekschool.dto.courses.CourseLectionsDto;
import com.geekschool.dto.lections.LectionDateDto;
import com.geekschool.dto.lections.LectionValidDto;
import com.geekschool.entity.CourseLection;
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
    public Lection createLection(LectionValidDto lectionValidDto) {
        Lection lection = new Lection();

        User teacher = userRepository.findByUsername(lectionValidDto.getTeacherName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        lection.setName(lectionValidDto.getName());
        lection.setDescription(lectionValidDto.getDescription());
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
    public void updateDateCourse(CourseLectionsDto courseLectionsDto) {
        List<LectionDateDto> lectionDateDtoList = courseLectionsDto.getLectionDateDtoList();

        Lection lection = findLectionById(lectionDateDtoList.get(0).getIdLection());

        List<CourseLection> courseLections = lection.getCourseLections();

        for (CourseLection courseLection: courseLections) {
            if (courseLection.getCourse().getId().equals(courseLectionsDto.getId())
                    && courseLection.getLection().getId().equals(courseLectionsDto.getLectionDateDtoList().get(0).getIdLection())) {
                courseLection.setCreationDate(lectionDateDtoList.get(0).getStartDate());
                courseLection.setEndDate(lectionDateDtoList.get(0).getEndDate());
            }
        }
        lection.setCourseLections(courseLections);
        lectionRepository.save(lection);
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
