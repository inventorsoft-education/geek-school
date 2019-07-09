package com.geekschool.repository;

import com.geekschool.entity.Role;
import com.geekschool.entity.Status;
import com.geekschool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u where u.username = :name")
    Optional<User> findByUsername(@Param("name") String name);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select u from User u where u.role = :role")
    List<User> findByRole(@Param("role") Role role);

    @Modifying
    @Query(value = "update User u set u.status = :status where u.id = :id")
    void updateStatusById(@Param("id") long id, @Param("status") Status status);
}
