package com.geekschool.service;

import com.geekschool.dto.GroupDto;
import com.geekschool.entity.Group;
import com.geekschool.entity.User;
import com.geekschool.mapper.GroupMapper;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class GroupService {

    private GroupRepository groupRepository;
    private GroupMapper groupMapper;
    private UserMapper userMapper;

    @Transactional
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public GroupDto getGroupById(long id) {
       return groupRepository.findById(id)
               .map(group -> groupMapper.convertToGroupDto(group))
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Group findByName(String name) {
        return groupRepository.findByName(name);
    }

    @Transactional
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public void deleteGroupById(long id) {
        groupRepository.deleteById(id);
    }

    @Transactional
    public void deleteUserFromGroup(long id, User user) {
        GroupDto groupDto = getGroupById(id);
        Set<User> students = groupDto.getStudents();
        students.remove(user);
        groupDto.setStudents(students);
        saveGroup(groupMapper.convertToGroup(groupDto));
    }

}
