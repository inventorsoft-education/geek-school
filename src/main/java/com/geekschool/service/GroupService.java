package com.geekschool.service;

import com.geekschool.dto.GroupDto;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.Group;
import com.geekschool.entity.User;
import com.geekschool.mapper.GroupMapper;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.GroupRepository;
import com.geekschool.repository.UserRepository;
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
    private UserService userService;
    private GroupMapper groupMapper;

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

    // TODO: 2019-07-05 fedya ne provtykav
    @Transactional
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public void deleteUserFromGroup(long id, User user) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));;
        Set<User> students = group.getStudents();
        students.remove(user);
        group.setStudents(students);
        saveGroup(group);
    }

}
