package com.geekschool.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id", "name"})
@Entity
@Table(name = "courses_templates", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class CourseTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String direction;

    @ToString.Exclude
    @ManyToMany
    private List<Lection> lections;

}
