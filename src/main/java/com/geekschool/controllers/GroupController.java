package com.geekschool.controllers;

import com.geekschool.dto.GroupDto;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import com.geekschool.mapper.GroupMapper;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.GroupService;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/groups")
public class GroupController {

    private GroupService groupService;
    private UserService userService;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private GroupMapper groupMapper;

    @GetMapping("admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<UserDto> getStudentsOfGroupById(@PathVariable long id) {
        GroupDto group = groupService.getGroupById(id);
        Set<UserDto> students = group.getStudents().stream()
                .map(u -> userMapper.convertToUserDto(u))
                .collect(Collectors.toSet());
        return students;
    }

    @GetMapping("admin")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getGroups() {
        return groupService.getGroups()
                .stream()
                .map(group -> groupMapper.convertToGroupDto(group))
                .collect(Collectors.toList());
    }

    @PutMapping("admin/add")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToGroup(@RequestParam("group_id") long idGroup,
                               @RequestParam("username") String username) {

        GroupDto group = groupService.getGroupById(idGroup);

        User user = userRepository.findByUsername(username).get();

        group.getStudents().add(user);

        groupService.saveGroup(groupMapper.convertToGroup(group));
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserFromGroup(@PathVariable long id,
                                    @RequestParam String username) {
        User user = userRepository.findByUsername(username).get();

        groupService.deleteUserFromGroup(id, user);
    }

}
