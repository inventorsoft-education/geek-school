package com.geekschool.repository;

import com.geekschool.entity.Lection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectionRepository extends JpaRepository<Lection, Long> {

    @Query("select l from Lection l where l.name = :name")
    Optional<Lection> findByName(@Param("name") String name);
}
