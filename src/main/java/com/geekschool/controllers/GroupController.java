package com.geekschool.controllers;

import com.geekschool.dto.GroupDto;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.Group;
import com.geekschool.entity.User;
import com.geekschool.mapper.GroupMapper;
import com.geekschool.mapper.UserMapper;
import com.geekschool.service.GroupService;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/groups")
public class GroupController {

    private GroupService groupService;
    private UserService userService;
    private UserMapper userMapper;
    private GroupMapper groupMapper;

    @GetMapping("admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<UserDto> getStudentsOfGroupById(@PathVariable long id) {
        Optional<Group> group = groupService.getGroupById(id);
        Set<UserDto> students = group.get().getStudents()
                        .stream()
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

        Optional<Group> group = groupService.getGroupById(idGroup);
        group.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Optional<User> user = userService.findByUsername(username);
        user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        group.get().getStudents().add(user.get());

        groupService.saveGroup(group.get());
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserFromGroup(@PathVariable long id,
                                    @RequestParam String username) {
        Optional<User> user = userService.findByUsername(username);
        user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        groupService.deleteUserFromGroup(id, user.get());
    }

}
