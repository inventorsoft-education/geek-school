package com.geekschool.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "courses_templates")
public class CourseTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "direction")
    private String direction;

    @ManyToMany
    @Column(name = "lections")
    private List<Lection> lections;

}
