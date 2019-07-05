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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
// TODO: 2019-07-05 move api prefix to application.properties and use it here
@RequestMapping("api/groups")
public class GroupController {

    private GroupService groupService;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private GroupMapper groupMapper;

    // TODO: 2019-07-05 remove admin prefix and use @PreAuthorize for security checks
    // TODO: 2019-07-05 refactor users loading to user service: define method to load users by group in userService
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<UserDto> getStudentsOfGroupById(@PathVariable Long id) {
        GroupDto group = groupService.getGroupById(id);
        return group.getStudents().stream()
                .map(u -> userMapper.convertToUserDto(u))
                .collect(Collectors.toSet());
    }

    @GetMapping("admin")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getGroups() {
        return groupService.getGroups()
                .stream()
                .map(group -> groupMapper.convertToGroupDto(group))
                .collect(Collectors.toList());
    }

    // TODO: 2019-07-05 change url POST /groups/{groupId}/users
    // TODO: 2019-07-05 move logic into service layer
    @PutMapping("admin")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToGroup(@RequestParam("group_id") long idGroup,
                               @RequestParam("username") String username) {

        GroupDto group = groupService.getGroupById(idGroup);

        User user = userRepository.findByUsername(username).get();

        group.getStudents().add(user);

    }

    // TODO: 2019-07-05 move user loading into service layer
    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserFromGroup(@PathVariable long id,
                                    @RequestParam String username) {
        User user = userRepository.findByUsername(username).get();

        groupService.deleteUserFromGroup(id, user);
    }

}
