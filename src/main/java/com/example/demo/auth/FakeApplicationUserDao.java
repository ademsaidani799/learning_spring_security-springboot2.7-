package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;


@Repository("fake")
public class FakeApplicationUserDao implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser ("annasmith",
                        passwordEncoder.encode("password"),
                        STUDENT.getGrantedAuthorities()),
                new ApplicationUser ("linda",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities()),
                new ApplicationUser ("tom",
                        passwordEncoder.encode("password"),
                        ADMINTRAINEE.getGrantedAuthorities())
        );
    }
}