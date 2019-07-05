package com.geekschool.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "token")
@Entity
@Table(name = "invited_tokens", uniqueConstraints = @UniqueConstraint(columnNames = "token"))
public class InvitedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(name = "date_off")
    private LocalDate dateOff;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}