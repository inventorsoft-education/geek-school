package com.geekschool.controllers;

import com.geekschool.dto.GroupDto;
import com.geekschool.dto.UserDto;
import com.geekschool.mapper.GroupMapper;
import com.geekschool.mapper.UserMapper;
import com.geekschool.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("${api}/groups")
public class GroupController {

    private GroupService groupService;
    private UserMapper userMapper;
    private GroupMapper groupMapper;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<UserDto> getStudentsOfGroupById(@PathVariable Long id) {
        return groupService.loadUsersByGroup(id)
                .stream()
                .map(u -> userMapper.convertToUserDto(u))
                .collect(Collectors.toSet());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getGroups() {
        return groupService.getGroups()
                .stream()
                .map(group -> groupMapper.convertToGroupDto(group))
                .collect(Collectors.toList());
    }

    @PutMapping("{groupId}/users")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToGroup(@PathVariable("groupId") Long groupId,
                               @RequestParam("username") String username) {
        groupService.addUserToGroup(groupId, username);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserFromGroup(@PathVariable long id,
                                    @RequestParam String username) {
        groupService.deleteUserFromGroup(id, username);
    }

}
