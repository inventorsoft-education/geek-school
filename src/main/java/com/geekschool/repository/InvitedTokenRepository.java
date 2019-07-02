package com.geekschool.repository;

import com.geekschool.entity.InvitedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitedTokenRepository extends JpaRepository<InvitedToken, Long> {

    InvitedToken findByToken(String token);
}
