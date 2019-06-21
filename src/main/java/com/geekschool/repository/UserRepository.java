package com.geekschool.repository;

import com.geekschool.constants.Status;
import com.geekschool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u where u.username = :name")
    User findByUsername(@Param("name") String name);


    @Query(value = "update User u set u.status = :status where u.id = :id")
    void updateStatusById(@Param("id") long id, @Param("status") Status status);
}
