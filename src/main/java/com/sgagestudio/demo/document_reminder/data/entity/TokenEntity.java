package com.sgagestudio.demo.document_reminder.data.entity;

import com.sgagestudio.demo.document_reminder.data.TokenType;
import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class TokenEntity {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity user;
}
