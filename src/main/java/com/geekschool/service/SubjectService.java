package com.geekschool.service;

import com.geekschool.dto.SubjectDto;
import com.geekschool.entity.Subject;
import com.geekschool.mapper.SubjectMapper;
import com.geekschool.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository subjectRepository;
    private SubjectMapper subjectMapper;

    @Transactional
    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Transactional
    public Subject findById(long id) {
        return subjectRepository.findById(id).get();
    }

    @Transactional
    public Subject findByName(String name) {
        return subjectRepository.findByName(name);
    }

    @Transactional
    public List<SubjectDto> getSubjects() {
        List<SubjectDto> subjects = subjectRepository.findAll().stream()
                .map(subject -> subjectMapper.convertToSubjectDto(subject))
                .collect(Collectors.toList());
       return subjects;
    }

    @Transactional
    public void deleteSubjectById(long id) {
        subjectRepository.deleteById(id);
    }
}
