package com.geekschool.service;

import com.geekschool.entity.Group;
import com.geekschool.entity.User;
import com.geekschool.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class GroupService {

    private GroupRepository groupRepository;

    @Transactional
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public Optional<Group>  getGroupById(long id) {
       Optional<Group> optionalGroup = groupRepository.findById(id);
       return optionalGroup;
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
        Optional<Group> optionalGroup = getGroupById(id);
        optionalGroup.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Group group = optionalGroup.get();
        Set<User> students = group.getStudents();
        students.remove(user);
        group.setStudents(students);
        saveGroup(group);
    }

}
