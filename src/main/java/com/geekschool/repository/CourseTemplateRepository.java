package com.geekschool.repository;

import com.geekschool.entity.CourseTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTemplateRepository extends JpaRepository<CourseTemplate, Long> {
}