package com.geekschool.repository;

import com.geekschool.entity.CourseTemplate;
import com.geekschool.entity.Lection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTemplateRepository extends JpaRepository<CourseTemplate, Long> {
}