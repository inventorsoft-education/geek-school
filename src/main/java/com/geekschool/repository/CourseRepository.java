package com.geekschool.repository;

import com.geekschool.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select s from Course s where s.name = :name")
    Course findByName(@Param("name") String name);
}
