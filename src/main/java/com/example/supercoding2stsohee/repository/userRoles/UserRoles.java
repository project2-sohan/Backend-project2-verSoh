package com.example.supercoding2stsohee.repository.userRoles;

import com.example.supercoding2stsohee.repository.roles.Roles;

import com.example.supercoding2stsohee.repository.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_roles_id")
    private Integer userRolesId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Roles roles;

}
