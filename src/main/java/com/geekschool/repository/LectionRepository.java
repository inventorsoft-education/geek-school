package com.geekschool.repository;

import com.geekschool.entity.Lection;
import com.geekschool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectionRepository extends JpaRepository<Lection, Long> {

    @Query("select l from Lection l where l.name = :name")
    Optional<Lection> findByName(@Param("name") String name);

    @Modifying
    @Query(value = "update Lection l set l.teacher = :teacher where l.id = :id")
    void updateTeacherById(@Param("id") long id, @Param("teacher") User teacher);
}
