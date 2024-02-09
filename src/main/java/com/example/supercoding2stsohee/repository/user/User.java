package com.example.supercoding2stsohee.repository.user;

import com.example.supercoding2stsohee.repository.userRoles.UserRoles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "userId")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_img", nullable = false)
    private String profile_img;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "failure_count", nullable = false)
    private Integer failureCount;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "delete_at")
    private LocalDateTime deletedAt;

    @Column(name = "lock_at")
    private LocalDateTime lockedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //cascade, orphanRemoval 추가해보았음
    private List<UserRoles> userRoles;
}
