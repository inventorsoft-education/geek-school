package com.geekschool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "lection")
@AllArgsConstructor
@NoArgsConstructor
public class Lection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "lection", cascade = CascadeType.ALL)
    private List<CourseLection> courseLections;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;
}

