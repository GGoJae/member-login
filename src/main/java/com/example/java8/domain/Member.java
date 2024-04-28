package com.example.java8.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 30, unique = true)
    private String loginId;

    @Column(length = 255)
    private String encryptedPassword;

    @Column(length = 30, unique = true)
    private String nickname;

    @Column(length = 50)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(updatable = false)
    private LocalDateTime enrolledTime;

    protected Member() {
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
