package com.geekschool.service;

import com.geekschool.entity.Group;
import com.geekschool.entity.User;
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
    private UserRepository userRepository;

    @Transactional
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public Group getGroupById(long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public void addUserToGroup(Long id, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Group group = getGroupById(id);
        group.getStudents().add(user);
        save(group);
    }

    @Transactional
    public Set<User> loadUsersByGroup(Long id) {
        return getGroupById(id).getStudents();
    }

    @Transactional
    public void deleteUserFromGroup(Long id, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Group group = getGroupById(id);
        Set<User> students = group.getStudents();
        students.remove(user);
        group.setStudents(students);
        save(group);
    }
}
