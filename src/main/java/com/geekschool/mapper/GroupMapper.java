package com.geekschool.mapper;

import com.geekschool.dto.GroupDto;
import com.geekschool.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public GroupDto convertToGroupDto(Group group) {
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getSubjects(),
                group.getStudents());
    }

    public Group convertToGroup(GroupDto groupDto) {
        return new Group(groupDto.getId(),
                groupDto.getName(),
                groupDto.getDescription(),
                groupDto.getSubjects(),
                groupDto.getStudents());
    }
}
