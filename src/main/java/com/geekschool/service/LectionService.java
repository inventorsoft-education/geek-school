package com.geekschool.service;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.Lection;
import com.geekschool.mapper.LectionMapper;
import com.geekschool.repository.LectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LectionService {

    private LectionRepository lectionRepository;
    private LectionMapper lectionMapper;

    @Transactional
    public LectionDto createLection(Lection lection) {
        return lectionMapper.convertToLectionDto(lectionRepository.save(lection));
    }

    @Transactional
    public LectionDto findLectionById(long id) {
        return lectionRepository.findById(id)
                .map(lection -> lectionMapper.convertToLectionDto(lection))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public LectionDto findLectionByName(String name) {
        return lectionRepository.findByName(name)
                .map(lection -> lectionMapper.convertToLectionDto(lection))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public List<LectionDto> getAllLections() {
        return lectionRepository.findAll().stream()
                .map(lection -> lectionMapper.convertToLectionDto(lection))
                .collect(Collectors.toList());
    }

    @Transactional
    public void geleteLectionById(long id) {
        lectionRepository.deleteById(id);
    }


}
