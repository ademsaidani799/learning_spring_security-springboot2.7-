package com.example.demo.security;

public enum ApplicationUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permisson;

    ApplicationUserPermission(String permisson){
        this.permisson = permisson;
    }

    public String getPermisson() {
        return permisson;
    }
}

