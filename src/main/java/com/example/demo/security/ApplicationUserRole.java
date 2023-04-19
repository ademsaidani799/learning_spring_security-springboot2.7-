package com.example.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_WRITE
            ,ApplicationUserPermission.COURSE_READ
            ,ApplicationUserPermission.STUDENT_WRITE
            ,ApplicationUserPermission.STUDENT_READ)),
    ADMINTRAINEE(Sets.newHashSet(ApplicationUserPermission.COURSE_READ
            ,ApplicationUserPermission.STUDENT_READ));


    private final Set<ApplicationUserPermission> permission;


    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }
}
