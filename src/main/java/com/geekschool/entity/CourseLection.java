package com.geekschool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "course_lection")
@NoArgsConstructor
public class CourseLection implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn
    private Lection lection;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime creationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    public CourseLection(Course course, Lection lection, LocalDateTime creationDate, LocalDateTime endDate) {
        this.course = course;
        this.lection = lection;
        this.creationDate = creationDate;
        this.endDate = endDate;
    }
}