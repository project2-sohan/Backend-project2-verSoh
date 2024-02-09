package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.repository.roles.RolesJpa;
import com.example.supercoding2stsohee.repository.userRoles.UserRolesJpa;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RolesJpa rolesJpa;
    private final UserRolesJpa userRolesJpa;
    private final UserJpa userJpa;
}
