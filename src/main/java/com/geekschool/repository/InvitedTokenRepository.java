package com.geekschool.repository;

import com.geekschool.entity.InvitedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvitedTokenRepository extends JpaRepository<InvitedToken, Long> {

    @Query("select it from InvitedToken it where it.token = :token")
    InvitedToken findByToken(@Param("token") String token);
}
